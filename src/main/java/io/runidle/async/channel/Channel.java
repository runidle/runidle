package io.runidle.async.channel;

import io.runidle.async.channel.message.ChannelMessage;

public interface Channel<C extends Channel<C>> {

    ChannelConfig channelConfig();

    /**
     * Write message
     *
     * @param message
     */
    void write(ChannelMessage message);

    /**
     * Specify messageType to write the message
     * Maybe, the specified messageType is not same with the actual message type
     *
     * @param messageType
     * @param message
     */
    void write(MessageType messageType, ChannelMessage message);

    <T, R> C handler(MessageType messageType, ChannelHandler<T, R> channelHandler);

    <T, R> C handler(MessageType messageType, ChannelHandler2<T, R> channelHandler);

    C start();

    void close();
}
