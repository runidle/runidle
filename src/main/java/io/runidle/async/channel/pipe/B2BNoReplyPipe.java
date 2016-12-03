package io.runidle.async.channel.pipe;

import io.runidle.async.channel.BusinessChannel;
import io.runidle.async.channel.request.B2BNoReplyRequest;

public interface B2BNoReplyPipe<D extends BusinessChannel<D>> extends ChannelNoReplyPipe<D> {
    B2BNoReplyRequest<? extends B2BNoReplyRequest, Object> noReply();
}
