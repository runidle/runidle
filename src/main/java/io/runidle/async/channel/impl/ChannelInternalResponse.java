package io.runidle.async.channel.impl;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class ChannelInternalResponse<T, R> {
    T requestContent;
    R responseContent;
    Throwable cause;
    String errMsg;
    boolean success;
}
