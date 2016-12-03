package io.runidle.async.channel.pipe;

import io.runidle.async.channel.message.ChannelInternalRequestMessage;

public interface ChannelPipeInterceptor {
    <T, R> void beforeSendRequest(ChannelInternalRequestMessage<T, R> requestMessage);

    <T, R> void wrapHandleResponse(ChannelInternalRequestMessage<T, R> responseMessage);

    <T, R> void beforeHandleRequest(ChannelInternalRequestMessage<T, R> requestMessage);

    <T, R> void beforeResponse(ChannelInternalRequestMessage<T, R> requestMessage);
}
