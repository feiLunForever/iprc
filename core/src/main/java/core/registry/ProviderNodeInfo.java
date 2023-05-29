package core.registry;

import lombok.Data;

@Data
public class ProviderNodeInfo {

    private String serviceName;

    private String address;

    private Integer weight;

    private String registryTime;

    @Override
    public String toString() {
        return "ProviderNodeInfo{" +
                "serviceName='" + serviceName + '\'' +
                ", address='" + address + '\'' +
                ", weight=" + weight +
                ", registryTime='" + registryTime + '\'' +
                '}';
    }
}