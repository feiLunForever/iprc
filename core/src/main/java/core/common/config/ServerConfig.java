package core.common.config;

import lombok.Data;

@Data
public class ServerConfig {

//    private Integer port;

    private Integer serverPort;

    private String registerAddr;

    private String applicationName;

    /**
     * 服务端序列化方式 example: hession2,kryo,jdk,fastjson
     */
    private String serverSerialize;
}
