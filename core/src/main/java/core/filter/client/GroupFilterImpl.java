package core.filter.client;



import core.common.ChannelFutureWrapper;
import core.common.RpcInvocation;
import core.common.utils.CommonUtils;
import core.filter.IClientFilter;

import java.util.List;

/**
 * 基于分组的过滤链路
 *
 * @Author linhao
 * @Date created in 2:20 下午 2022/1/29
 */
public class GroupFilterImpl implements IClientFilter {

    @Override
    public void doFilter(List<ChannelFutureWrapper> src, RpcInvocation rpcInvocation) {
        String group = String.valueOf(rpcInvocation.getAttachments().get("group"));
        for (ChannelFutureWrapper channelFutureWrapper : src) {
            if (!channelFutureWrapper.getGroup().equals(group)) {
                src.remove(channelFutureWrapper);
            }
        }
        if (CommonUtils.isEmptyList(src)) {
            throw new RuntimeException("no provider match for group " + group);
        }
    }
}
