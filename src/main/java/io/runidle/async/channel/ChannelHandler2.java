package io.runidle.async.channel;

import io.runidle.async.channel.message.ChannelMessageContext;

public interface ChannelHandler2<T, R> {
    void handle(ChannelMessageContext<R> messageContext, T requestContent);
}
