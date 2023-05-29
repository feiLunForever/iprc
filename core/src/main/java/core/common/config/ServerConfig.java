package core.common.config;

import lombok.Data;

@Data
public class ServerConfig {

//    private Integer port;

    private Integer serverPort;

    private String registerAddr;

    private String applicationName;
}
