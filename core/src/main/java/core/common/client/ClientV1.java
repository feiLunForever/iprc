//package core.common.client;
//
//import com.alibaba.fastjson.JSON;
//import core.common.RpcDecoder;
//import core.common.RpcEncoder;
//import core.common.RpcInvocation;
//import core.common.RpcProtocol;
//import core.common.cache.CommonClientCache;
//import core.common.config.ClientConfig;
//import core.proxy.jdk.JDKProxyFactory;
//import interfaces.DataService;
//import io.netty.bootstrap.Bootstrap;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioSocketChannel;
//import lombok.Getter;
//import lombok.Setter;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class ClientV1 {
//    private Logger logger = LoggerFactory.getLogger(ClientV1.class);
//
//    public static EventLoopGroup clientGroup = new NioEventLoopGroup();
//
//    @Getter
//    @Setter
//    private ClientConfig clientConfig;
//
//    public RpcReference startClientApplication() throws InterruptedException {
//        EventLoopGroup clientGroup = new NioEventLoopGroup();
//        Bootstrap bootstrap = new Bootstrap();
//        bootstrap.group(clientGroup);
//        bootstrap.channel(NioSocketChannel.class);
//        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
//            @Override
//            protected void initChannel(SocketChannel ch) throws Exception {
//                ch.pipeline().addLast(new RpcEncoder());
//                ch.pipeline().addLast(new RpcDecoder());
//                ch.pipeline().addLast(new ClientHandler());
//            }
//        });
//
//        ChannelFuture channelFuture = bootstrap.connect(clientConfig.getServerAddr(), clientConfig.getPort()).sync();
//        logger.info("============ 服务启动 ============");
//        this.startClient(channelFuture);
//
//        RpcReference rpcReference = new RpcReference(new JDKProxyFactory());
//        return rpcReference;
//    }
//
//    /**
//     * 开启发送线程
//     *
//     * @param channelFuture
//     */
//    private void startClient(ChannelFuture channelFuture) {
//        Thread asyncSendJob = new Thread(new AsyncSendJob(channelFuture));
//        asyncSendJob.start();
//    }
//
//    class AsyncSendJob implements Runnable {
//        private ChannelFuture channelFuture;
//
//        public AsyncSendJob(ChannelFuture channelFuture) {
//            this.channelFuture = channelFuture;
//        }
//
//        @Override
//        public void run() {
//            while (true) {
//                try { // 阻塞模式
//                    RpcInvocation data = CommonClientCache.SEND_QUEUE.take();
//                    String json = JSON.toJSONString(data);
//                    RpcProtocol rpcProtocol = new RpcProtocol(json.getBytes());
//                    channelFuture.channel().writeAndFlush(rpcProtocol);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public static void main(String[] args) throws Throwable {
//        ClientV1 client = new ClientV1();
//        ClientConfig clientConfig = new ClientConfig();
//        clientConfig.setPort(9090);
//        clientConfig.setServerAddr("localhost");
//        client.setClientConfig(clientConfig);
//
//        RpcReference rpcReference = client.startClientApplication();
//
//        DataService dataService = rpcReference.get(DataService.class);
//        for (int i = 0; i < 100; i++) {
//            String result = dataService.sendData("test");
//            System.out.println(result);
//        }
//    }
//
//
//}
