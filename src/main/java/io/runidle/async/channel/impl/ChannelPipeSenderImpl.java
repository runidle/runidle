package io.runidle.async.channel.impl;

import io.runidle.async.channel.Channel;
import io.runidle.async.channel.message.ChannelInternalRequestMessage;
import io.runidle.async.channel.pipe.ChannelPipeInterceptor;
import io.runidle.async.channel.pipe.ChannelPipeSender;

public class ChannelPipeSenderImpl implements ChannelPipeSender {
    private Channel destChannel;
    private ChannelPipeInterceptor channelPipeInterceptor;

    protected ChannelPipeSenderImpl(Channel destChannel,
                                    ChannelPipeInterceptor channelPipeInterceptor) {
        this.destChannel = destChannel;
        this.channelPipeInterceptor = channelPipeInterceptor;
    }

    @Override
    public <T, R> void send(ChannelInternalRequestMessage<T, R> message) {
        this.channelPipeInterceptor.beforeSendRequest(message);
        if (message.features().sendable())
            this.destChannel.write(message);
    }

    @Override
    public <T, R> void resend(ChannelInternalRequestMessage<T, R> message) {
        if (message.features().sendable())
            this.destChannel.write(message);
    }

    @Override
    public <T, R> void handleResponse(ChannelInternalRequestMessage<T, R> message) {
        this.channelPipeInterceptor.wrapHandleResponse(message);
    }

    @Override
    public String toString() {
        return "Sender Destination Channel: " + (this.destChannel == null ? "null" : this.destChannel.channelConfig().getName());
    }
}
