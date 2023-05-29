package core.common.cache;

import com.google.common.collect.Lists;
import core.common.ChannelFuturePollingRef;
import core.common.ChannelFutureWrapper;
import core.common.RpcInvocation;
import core.router.IRouter;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 公用缓存 存储请求队列等公共信息
 */
public class CommonClientCache {

    public static BlockingQueue<RpcInvocation> SEND_QUEUE = new ArrayBlockingQueue(100);
    public static Map<String, Object> RESP_MAP = new ConcurrentHashMap<>();

    /**
     * provider名称 --> 该服务有哪些集群URL
     */
    public static List<String> SUBSCRIBE_SERVICE_LIST = Lists.newArrayList();

    public static Set<String> SERVER_ADDRESS = new HashSet<>();

    /**
     * 每次进行远程调用的时候都是从这里面去选择服务提供者
     */
    public static Map<String, List<ChannelFutureWrapper>> CONNECT_MAP = new ConcurrentHashMap<>();

    //随机请求的map
    public static Map<String, ChannelFutureWrapper[]> SERVICE_ROUTER_MAP = new ConcurrentHashMap<>();
    public static ChannelFuturePollingRef CHANNEL_FUTURE_POLLING_REF = new ChannelFuturePollingRef();
    public static IRouter IROUTER;
}