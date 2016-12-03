package io.runidle.async.channel.pipe;

import io.runidle.async.channel.BusinessChannel;
import io.runidle.async.channel.request.S2BNoReplyRequest;

public interface S2BNoReplyPipe<D extends BusinessChannel<D>> extends ChannelNoReplyPipe<D> {
    S2BNoReplyRequest<? extends S2BNoReplyRequest, Object> noReply();
}