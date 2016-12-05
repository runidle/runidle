package io.runidle.async.channel.pipe;

import io.runidle.async.channel.ChannelHandler;
import io.runidle.async.channel.message.ChannelInternalRequestMessage;

public interface ChannelPipeAcceptor {
    <T, R> void response(ChannelInternalRequestMessage<T, R> message);

    <T, R> void handleRequest(ChannelInternalRequestMessage<T, R> message, ChannelHandler<T, R> channelHandler);
}
