package io.runidle.async.channel.impl;

import io.runidle.async.channel.feature.idem.IdempotentHandler;
import io.runidle.async.channel.message.ChannelInternalRequestMessage;
import io.runidle.async.channel.pipe.ChannelPipeInterceptor;

/**
 * For B2BPipe and B2CPipe,
 * (1) Retry is triggered on Business Channel(wrapHandleResponse)
 * (2) Timeout is started on Business Channel (beforeSendRequest).
 * (3) Idem Control is triggered on Business Channel (beforeSendRequest and wrapHandleResponse)
 */
public class FromBusinessChannelInterceptorImpl implements ChannelPipeInterceptor {
    private IdempotentHandler idempotentHandler = new IdempotentHandler();

    @Override
    public <T, R> void beforeSendRequest(ChannelInternalRequestMessage<T, R> requestMessage) {
        this.idempotentHandler.handle(requestMessage);
        if (requestMessage.features().timeout() != null) {
            requestMessage.features().timeout().start();
        }
    }

    @Override
    public <T, R> void wrapHandleResponse(ChannelInternalRequestMessage<T, R> requestMessage) {
        if (!doRetry(requestMessage)) {
            this.idempotentHandler.end(requestMessage);
            requestMessage.finish();
            if (requestMessage.features().idem() != null) {
                requestMessage.features().idem().finishFollowers();
            }
        }
    }

    @Override
    public <T, R> void beforeHandleRequest(ChannelInternalRequestMessage<T, R> requestMessage) {

    }

    @Override
    public <T, R> void beforeResponse(ChannelInternalRequestMessage<T, R> requestMessage) {

    }

    private <T, R> boolean doRetry(ChannelInternalRequestMessage<T, R> requestMessage) {
        if (requestMessage.features().timeout() != null
                && requestMessage.features().timeout().isDone()) {
            return false;
        }
        return requestMessage.features().retry() != null &&
                requestMessage.features().retry().retry();
    }
}
