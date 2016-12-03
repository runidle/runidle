package io.runidle.async.channel.pipe;

import io.runidle.async.channel.Channel;
import io.runidle.async.channel.request.ChannelNoReplyRequest;

public interface ChannelNoReplyPipe<D extends Channel> extends ChannelPipe {
    /**
     * DestinationChannel
     *
     * @return
     */
    D destChannel();

    /**
     * Create ChannelNoReplyRequest for this pipe without messageType
     *
     * @return
     */
    ChannelNoReplyRequest<? extends ChannelNoReplyRequest, Object> noReply();
}
