package io.runidle.async.channel.pipe;

import io.runidle.async.channel.request.ChannelInternalRequest;

public interface ChannelInternalPipe
        extends B2BPipe, B2CPipe, S2BPipe, ChannelPipeContext {
    ChannelPipeAcceptor acceptor();

    ChannelPipeSender sender();

    boolean hasReply();

    ChannelInternalRequest<? extends ChannelInternalRequest, Object, Object> request();

    ChannelInternalRequest<? extends ChannelInternalRequest, Object, Object> noReply();
}
