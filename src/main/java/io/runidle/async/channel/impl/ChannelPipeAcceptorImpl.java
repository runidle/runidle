package io.runidle.async.channel.impl;

import io.runidle.async.channel.Channel;
import io.runidle.async.channel.ChannelHandler;
import io.runidle.async.channel.message.ChannelInternalRequestMessage;
import io.runidle.async.channel.pipe.ChannelPipeAcceptor;
import io.runidle.async.channel.pipe.ChannelPipeInterceptor;
import io.runidle.async.channel.request.DefaultMessageType;

public class ChannelPipeAcceptorImpl implements ChannelPipeAcceptor {
    private Channel srcChannel;
    private ChannelPipeInterceptor channelPipeInterceptor;

    protected ChannelPipeAcceptorImpl(Channel srcChannel,
                                      ChannelPipeInterceptor channelPipeInterceptor) {
        this.srcChannel = srcChannel;
        this.channelPipeInterceptor = channelPipeInterceptor;
    }

    @Override
    public <T, R> void responseTimeout(ChannelInternalRequestMessage<T, R> message) {
        this.srcChannel.write(DefaultMessageType.Timeout, message);
    }

    @Override
    public <T, R> void response(ChannelInternalRequestMessage<T, R> message) {
        this.channelPipeInterceptor.beforeResponse(message);
        this.srcChannel.write(DefaultMessageType.Response, message);
    }

    @Override
    public <T, R> void handleRequest(ChannelInternalRequestMessage<T, R> message,
                                     ChannelHandler<T, R> channelHandler) {
        this.channelPipeInterceptor.beforeHandleRequest(message);
        channelHandler.handle(message);
    }

    @Override
    public String toString() {
        return "Pipe Acceptor Source Channel: " + (this.srcChannel == null ? "null" : this.srcChannel.channelConfig().getName());
    }
}
