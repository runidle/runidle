package io.runidle.async.channel.impl;

import io.runidle.async.channel.feature.idem.IdempotentHandler;
import io.runidle.async.channel.message.ChannelInternalRequestMessage;
import io.runidle.async.channel.pipe.ChannelPipeInterceptor;


/**
 * For S2BPipe,
 * (1) No Retry
 * (2) Timeout is started on Business Channel (beforeHandleRequest).
 * (3) Idem Control is trigger on Business Channel (beforeHandleRequest and beforeResponse)
 */
public class FromServerChannelInterceptorImpl implements ChannelPipeInterceptor {
    private IdempotentHandler idempotentHandler = new IdempotentHandler();

    @Override
    public <T, R> void beforeSendRequest(ChannelInternalRequestMessage<T, R> requestMessage) {

    }

    @Override
    public <T, R> void wrapHandleResponse(ChannelInternalRequestMessage<T, R> responseMessage) {

    }

    @Override
    public <T, R> void beforeHandleRequest(ChannelInternalRequestMessage<T, R> requestMessage) {
        this.idempotentHandler.handle(requestMessage);
        if (requestMessage.features().timeout() != null) {
            requestMessage.features().timeout().start();
        }
    }

    @Override
    public <T, R> void beforeResponse(ChannelInternalRequestMessage<T, R> requestMessage) {
        this.idempotentHandler.end(requestMessage);
        requestMessage.finish();
        if (requestMessage.features().idem() != null) {
            requestMessage.features().idem().finishFollowers();
        }
    }
}
