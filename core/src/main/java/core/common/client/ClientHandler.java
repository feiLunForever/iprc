package core.common.client;

import com.alibaba.fastjson.JSON;
import core.common.RpcInvocation;
import core.common.RpcProtocol;
import core.common.cache.CommonClientCache;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RpcProtocol rpcProtocol = (RpcProtocol) msg;
        byte[] reqContent = rpcProtocol.getContent();
        String json = new String(reqContent, 0, reqContent.length);
        RpcInvocation rpcInvocation = JSON.parseObject(json, RpcInvocation.class);
        if (!CommonClientCache.RESP_MAP.containsKey(rpcInvocation.getUuid())) {
            throw new IllegalArgumentException("server response is error!");
        }

        CommonClientCache.RESP_MAP.put(rpcInvocation.getUuid(), rpcInvocation);

        ReferenceCountUtil.release(msg);
    }
}
