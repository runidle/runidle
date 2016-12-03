package io.runidle.async.channel.impl;

import io.runidle.async.channel.pipe.ChannelPipeContext;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

public class ChannelMessageImpl<M extends ChannelMessageImpl<M, T, R>, T, R>
        extends AbstractChannelMessageImpl<M, T, R> {

    private FailHandler<T> failHandler;
    private SuccessHandler<T, R> successHandler;
    @Getter
    @Setter
    @Accessors(fluent = true)
    private boolean responded = false;
    private boolean noReply = false;

    public ChannelMessageImpl(ChannelPipeContext channelPipeContext) {
        super(channelPipeContext);
        if (channelPipeContext.channelPipe() != null)
            this.noReply = !channelPipeContext.channelPipe().hasReply();
    }

    @Override
    public void end() {
        //Don't use Preconditions.checkNotNull, low performance
        if (this.messageType() == null) {
            throw new NullPointerException("MessageType is required for " + this.toString());
        }

        if (!noReply && (this.successHandler == null || this.failHandler == null)) {
            throw new NullPointerException("SuccessHandler and FailHandler is required for " + this.toString());
        }
        this.channelPipe().sender().send(this);
    }

    @Override
    public M onFail(FailHandler<T> failHandler) {
        this.failHandler = failHandler;
        return (M) this;
    }

    @Override
    public M onSuccess(SuccessHandler<T, R> successHandler) {
        this.successHandler = successHandler;
        return (M) this;
    }

    @Override
    public void fail(String errMsg, Throwable ex) {
        if (this.responded || this.finished()) return;
        responded = true;
        if (!this.noReply) {
            ChannelInternalResponse<T, R> response;
            response = new ChannelInternalResponse<>();
            response.requestContent(this.requestContent())
                    .cause(ex).errMsg(errMsg).success(false);
            super.response(response);
            this.channelPipe().acceptor().response(this);
        }
    }

    @Override
    public void succeed(R responseContent) {
        if (this.responded || this.finished()) return;
        responded = true;
        if (!this.noReply) {
            ChannelInternalResponse<T, R> response;
            response = new ChannelInternalResponse<>();
            response.requestContent(this.requestContent())
                    .responseContent(responseContent).success(true);
            super.response(response);
            this.channelPipe().acceptor().response(this);
        }
    }

    @Override
    public void finish() {
        if (this.finished()) return;
        this.finished(true);
        if (this.noReply) return;
        if (this.response().success()) {
            this.successHandler.handle(this.response().requestContent(),
                    this.response().responseContent());
        } else {
            this.failHandler.handle(this.requestContent(),
                    this.response().errMsg(), this.response().cause());
        }
    }

    @Override
    public M noReply() {
        this.noReply = true;
        return (M) this;
    }
}
