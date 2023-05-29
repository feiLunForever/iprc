package core.common;

import io.netty.channel.ChannelFuture;
import lombok.Data;

@Data
public class ChannelFutureWrapper {

    private ChannelFuture channelFuture;

    private String host;

    private Integer port;

}
