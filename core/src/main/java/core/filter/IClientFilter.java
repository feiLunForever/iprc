package core.filter;

import core.common.ChannelFutureWrapper;
import core.common.RpcInvocation;

import java.util.List;

public interface IClientFilter extends IFilter {

    /**
     * 执行过滤链
     *
     * @param src
     * @param rpcInvocation
     * @return
     */
    void doFilter(List<ChannelFutureWrapper> src, RpcInvocation rpcInvocation);
}