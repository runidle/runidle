package io.runidle.async.channel;

import io.runidle.async.channel.message.ChannelMessage;

public interface ChannelHandler<T, R> {
    void handle(ChannelMessage<T, R> requestMessage);
}
