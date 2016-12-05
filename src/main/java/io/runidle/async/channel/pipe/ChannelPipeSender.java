package io.runidle.async.channel.pipe;

import io.runidle.async.channel.message.ChannelInternalRequestMessage;

public interface ChannelPipeSender {
    <T, R> void send(ChannelInternalRequestMessage<T, R> message);

    <T, R> void resend(ChannelInternalRequestMessage<T, R> message);

    <T, R> void handleResponse(ChannelInternalRequestMessage<T, R> message);
}
