package core.common.event.listener;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import core.common.ChannelFutureWrapper;
import core.common.cache.CommonClientCache;
import core.common.client.ConnectionHandler;
import core.common.event.IRpcListener;
import core.common.event.IRpcUpdateEvent;
import core.common.event.data.URLChangeWrapper;
import io.netty.channel.ChannelFuture;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

public class ServiceUpdateListener implements IRpcListener<IRpcUpdateEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceUpdateListener.class);

    @Override
    public void callBack(Object t) {
        URLChangeWrapper urlChangeWrapper = (URLChangeWrapper) t;
        List<ChannelFutureWrapper> channelFutureWrappers = CommonClientCache.CONNECT_MAP.get(urlChangeWrapper.getServiceName());
        if (CollectionUtils.isEmpty(channelFutureWrappers)) {
            LOGGER.error("[ServiceUpdateListener] channelFutureWrappers is empty");
            return;
        }
        List<String> matchProviderUrl = urlChangeWrapper.getProviderUrl();
        Set<String> finalUrl = Sets.newHashSet();
        List<ChannelFutureWrapper> finalChannelFutureWrappers = Lists.newArrayList();
        for (ChannelFutureWrapper channelFutureWrapper : channelFutureWrappers) {
            String oldServerAddress = channelFutureWrapper.getHost() + ":" + channelFutureWrapper.getPort();
            if (!matchProviderUrl.contains(oldServerAddress)) { // 如果老的url没有，说明已经被移除了
                continue;
            }
            finalChannelFutureWrappers.add(channelFutureWrapper);
            finalUrl.add(oldServerAddress);
        }
        List<ChannelFutureWrapper> newChannelFutureWrapper = Lists.newArrayList(); // 此时老的url已经被移除了，开始检查是否有新的url
        for (String newProviderUrl : matchProviderUrl) {
            if (!finalUrl.contains(newProviderUrl)) {
                ChannelFutureWrapper channelFutureWrapper = new ChannelFutureWrapper();
                String host = newProviderUrl.split(":")[0];
                Integer port = Integer.valueOf(newProviderUrl.split(":")[1]);
                channelFutureWrapper.setPort(port);
                channelFutureWrapper.setHost(host);
                ChannelFuture channelFuture = null;
                try {
                    channelFuture = ConnectionHandler.createChannelFuture(host, port);
                    channelFutureWrapper.setChannelFuture(channelFuture);
                    newChannelFutureWrapper.add(channelFutureWrapper);
                    finalUrl.add(newProviderUrl);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        finalChannelFutureWrappers.addAll(newChannelFutureWrapper);
        //最终更新服务在这里
        CommonClientCache.CONNECT_MAP.put(urlChangeWrapper.getServiceName(), finalChannelFutureWrappers);
    }

}
