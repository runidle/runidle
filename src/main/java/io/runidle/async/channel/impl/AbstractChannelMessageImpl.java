package io.runidle.async.channel.impl;

import io.runidle.async.channel.MessageType;
import io.runidle.async.channel.feature.ChannelMessageFeatures;
import io.runidle.async.channel.message.ChannelInternalRequestMessage;
import io.runidle.async.channel.message.ChannelMessageContext;
import io.runidle.async.channel.pipe.ChannelInternalPipe;
import io.runidle.async.channel.pipe.ChannelPipeContext;
import io.runidle.async.channel.request.ChannelInternalRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public abstract class AbstractChannelMessageImpl<M extends AbstractChannelMessageImpl<M, T, R>, T, R>
        implements ChannelInternalRequest<M, T, R>,
        ChannelInternalRequestMessage<T, R> {
    private T requestContent;
    private MessageType messageType;
    private ChannelInternalPipe channelPipe;

    private ChannelMessageFeatures<T, R> features;

    @Setter(AccessLevel.PROTECTED)
    @Accessors(fluent = true)
    private boolean finished;
    @Setter(AccessLevel.PROTECTED)
    @Accessors(fluent = true)
    private ChannelInternalResponse<T, R> response;

    protected AbstractChannelMessageImpl(ChannelPipeContext channelPipeContext) {
        this.channelPipe = channelPipeContext.channelPipe();
        this.features = new ChannelMessageFeatures<>(this);
    }

    public ChannelMessageContext<R> messageContext() {
        return this;
    }

    @Override
    public M messageType(MessageType messageType) {
        this.messageType = messageType;
        return (M) this;
    }

    @Override
    public M requestContent(T requestContent) {
        this.requestContent = requestContent;
        return (M) this;
    }

    @Override
    public M idem(Object key) {
        this.features.idem(key);
        return (M) this;
    }

    @Override
    public M timeout(long timeoutMS) {
        this.features.timeout(timeoutMS);
        return (M) this;
    }

    @Override
    public M retry(int retryCount, long intervalMs) {
        this.features.retry(retryCount, intervalMs);
        return (M) this;
    }

    @Override
    public String toString() {
        return "ChannelPipe: " + this.channelPipe.toString() + "; requestContent: " + requestContent;
    }
}
