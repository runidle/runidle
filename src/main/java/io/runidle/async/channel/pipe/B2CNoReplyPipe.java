package io.runidle.async.channel.pipe;

import io.runidle.async.channel.ClientChannel;
import io.runidle.async.channel.request.B2CNoReplyRequest;

public interface B2CNoReplyPipe<D extends ClientChannel> extends ChannelNoReplyPipe<D> {
    B2CNoReplyRequest<? extends B2CNoReplyRequest, Object> noReply();
}