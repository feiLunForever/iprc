package core.filter.client;

import core.common.ChannelFutureWrapper;
import core.common.RpcInvocation;
import core.filter.IClientFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static core.common.cache.CommonClientCache.CLIENT_CONFIG;


/**
 * 客户端调用日志过滤器
 *
 * @Author linhao
 * @Date created in 8:01 下午 2022/1/29
 */
public class ClientLogFilterImpl implements IClientFilter {

    private static Logger logger = LoggerFactory.getLogger(ClientLogFilterImpl.class);

    @Override
    public void doFilter(List<ChannelFutureWrapper> src, RpcInvocation rpcInvocation) {
        rpcInvocation.getAttachments().put("c_app_name",CLIENT_CONFIG.getApplicationName());
        logger.info(rpcInvocation.getAttachments().get("c_app_name")+" do invoke -----> "+rpcInvocation.getTargetServiceName());
    }

}
