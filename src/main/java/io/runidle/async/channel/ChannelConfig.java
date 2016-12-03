package io.runidle.async.channel;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ChannelConfig {
    private String name = "Channel";
    private int queueSize = 1024;
}
