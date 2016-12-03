package io.runidle.async.channel.message;

import io.runidle.async.channel.MessageType;

public interface ChannelMessage<T, R> {
    MessageType messageType();

    T requestContent();

    ChannelMessageContext<R> messageContext();
}
