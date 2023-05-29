package core.common.event.listener;

import core.common.ChannelFutureWrapper;
import core.common.event.IRpcListener;
import core.common.event.IRpcNodeChangeEvent;
import core.registry.ProviderNodeInfo;
import core.registry.URL;

import java.util.List;

import static core.common.cache.CommonClientCache.CONNECT_MAP;
import static core.common.cache.CommonClientCache.IROUTER;

public class ProviderNodeDataChangeListener implements IRpcListener<IRpcNodeChangeEvent> {

    @Override
    public void callBack(Object t) {
        ProviderNodeInfo providerNodeInfo = ((ProviderNodeInfo) t);
        List<ChannelFutureWrapper> channelFutureWrappers =  CONNECT_MAP.get(providerNodeInfo.getServiceName());
        for (ChannelFutureWrapper channelFutureWrapper : channelFutureWrappers) {
            String address = channelFutureWrapper.getHost()+":"+channelFutureWrapper.getPort();
            if(address.equals(providerNodeInfo.getAddress())){
                //修改权重
                channelFutureWrapper.setWeight(providerNodeInfo.getWeight());
                URL url = new URL();
                url.setServiceName(providerNodeInfo.getServiceName());
                //更新权重
                IROUTER.updateWeight(url);
                break;
            }
        }
    }
}