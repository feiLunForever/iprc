package core.server;


import core.common.cache.CommonServerCache;
import core.common.config.ServerConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {

    private ServerConfig serverConfig;

    private static EventLoopGroup bossGroup = null;

    private static EventLoopGroup workerGroup = null;

    public void setServerConfig(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

    public void registryService(Object serviceBean) {
        Class<?>[] classes = serviceBean.getClass().getInterfaces();
        if (classes.length == 0)
            throw new RuntimeException("service must had interfaces!");
        if (classes.length > 1)
            throw new RuntimeException("service must only had one interfaces!");
        CommonServerCache.PROVIDER_CLASS_MAP.put(classes[0].getName(), serviceBean);

    }

    public void startApplication() {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
        bootstrap.option(ChannelOption.SO_SNDBUF, 16 * 1024)
                .option(ChannelOption.SO_RCVBUF, 16 * 1024)
                .option(ChannelOption.SO_KEEPALIVE, true);

        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                System.out.println("初始化provider过程");
//                ch.pipeline().addLast(new RpcEncoder());
//                ch.pipeline().addLast(new RpcDecoder());
//                ch.pipeline().addLast(new ServerHandler());
            }
        });
    }

    public static void main(String[] args) {
        Server server = new Server();
        ServerConfig serverConfig = new ServerConfig();
        serverConfig.setPort(9090);
        server.setServerConfig(serverConfig);
        server.registryService(new DataServiceImpl());
        server.startApplication();
    }
}
