package core.registry;

import lombok.Data;

@Data
public class ProviderNodeInfo {

    private String serviceName;

    private String address;

    @Override
    public String toString() {
        return "ProviderNodeInfo{" +
                "serviceName='" + serviceName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}