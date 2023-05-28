package core.server;

import com.alibaba.fastjson.JSON;
import core.common.RpcInvocation;
import core.common.RpcProtocol;
import core.common.cache.CommonServerCache;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RpcProtocol rpcProtocol = (RpcProtocol) msg;
        String json = new String(rpcProtocol.getContent(),0,rpcProtocol.getContentLength());
        RpcInvocation rpcInvocation = JSON.parseObject(json,RpcInvocation.class);
        Object aimObject = CommonServerCache.PROVIDER_CLASS_MAP.get(rpcInvocation.getTargetServiceName());
        Method[] methods = aimObject.getClass().getDeclaredMethods();
        Object result = null;
        for (Method method : methods) {
            if (method.getName().equals(rpcInvocation.getTargetMethod())) {
                if (method.getReturnType().equals(Void.TYPE)) {
                    method.invoke(aimObject, rpcInvocation.getArgs());
                } else {
                    result = method.invoke(aimObject, rpcInvocation.getArgs());
                }
                break;
            }
        }
        rpcInvocation.setResponse(result);
        RpcProtocol respRpcProtocol = new RpcProtocol(JSON.toJSONString(rpcInvocation).getBytes());

        ctx.writeAndFlush(respRpcProtocol);
    }
}
