package core.common.config;

import lombok.Data;

@Data
public class ClientConfig {

//    private Integer port;
//
//    private String serverAddr;

    private String applicationName;

    private String registerAddr;

    /**
     * 代理类型 example: jdk,javassist
     */
    private String proxyType;

    /**
     * 负载均衡策略 example:random,rotate
     */
    private String routerStrategy;
}
