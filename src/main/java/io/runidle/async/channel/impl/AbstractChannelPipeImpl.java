package io.runidle.async.channel.impl;

import io.runidle.async.channel.Channel;
import io.runidle.async.channel.pipe.ChannelInternalPipe;
import io.runidle.async.channel.pipe.ChannelPipeAcceptor;
import io.runidle.async.channel.pipe.ChannelPipeInterceptor;
import io.runidle.async.channel.pipe.ChannelPipeSender;
import io.runidle.async.channel.request.ChannelInternalRequest;
import io.runidle.async.channel.scheduler.ChannelSchedule;
import io.runidle.async.channel.scheduler.ChannelScheduler;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public abstract class AbstractChannelPipeImpl implements ChannelInternalPipe {
    private Channel srcChannel;
    private Channel destChannel;

    private ChannelPipeAcceptor acceptor;
    private ChannelPipeSender sender;
    private ChannelPipeInterceptor interceptor;

    public AbstractChannelPipeImpl(Channel srcChannel, Channel destChannel
            , ChannelPipeInterceptor interceptor) {
        this.srcChannel = srcChannel;
        this.destChannel = destChannel;
        this.interceptor = interceptor;
        this.acceptor = new ChannelPipeAcceptorImpl(this.srcChannel, this.interceptor);
        this.sender = new ChannelPipeSenderImpl(this.destChannel, this.interceptor);
    }

    @Override
    public ChannelSchedule schedule() {
        return ChannelScheduler.schedule();
    }

    @Override
    public boolean hasReply() {
        return true;
    }

    @Override
    public ChannelInternalRequest<? extends ChannelInternalRequest, Object, Object> request() {
        return new ChannelMessageImpl<>(this);
    }

    @Override
    public ChannelInternalRequest<? extends ChannelInternalRequest, Object, Object> noReply() {
        return new ChannelMessageImpl<>(this);
    }

    @Override
    public ChannelInternalPipe channelPipe() {
        return this;
    }

    @Override
    public String toString() {
        return this.acceptor.toString() + "," +
                this.sender.toString();
    }
}

