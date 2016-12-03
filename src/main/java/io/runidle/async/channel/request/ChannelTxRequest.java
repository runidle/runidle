package io.runidle.async.channel.request;

public interface ChannelTxRequest<M extends ChannelTxRequest<M, T, R>, T, R>
        extends ChannelRequest<M, T> {
    /**
     * Set timeout for this request
     *
     * @param timeoutMS
     * @return
     */
    M timeout(long timeoutMS);

    /**
     * Set the failure response handler
     *
     * @param failHandler
     * @return
     */
    M onFail(FailHandler<T> failHandler);

    /**
     * Set the success response handler
     *
     * @param successHandler
     * @return
     */
    M onSuccess(SuccessHandler<T, R> successHandler);

    interface FailHandler<T> {
        void handle(T requestContent, String errMsg, Throwable cause);
    }

    interface SuccessHandler<T, R> {
        void handle(T requestContent, R responseContent);
    }
}
