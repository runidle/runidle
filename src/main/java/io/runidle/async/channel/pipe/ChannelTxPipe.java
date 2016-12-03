package io.runidle.async.channel.pipe;

import io.runidle.async.channel.Channel;
import io.runidle.async.channel.request.ChannelTxRequest;

public interface ChannelTxPipe<S extends Channel, D extends Channel> extends ChannelNoReplyPipe<D> {

    /**
     * Source Channel
     *
     * @return
     */
    S srcChannel();

    ChannelTxRequest<? extends ChannelTxRequest, Object, Object> request();
}
