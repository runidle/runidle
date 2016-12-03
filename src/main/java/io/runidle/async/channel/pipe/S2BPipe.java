package io.runidle.async.channel.pipe;

import io.runidle.async.channel.BusinessChannel;
import io.runidle.async.channel.ServerChannel;
import io.runidle.async.channel.request.S2BRequest;

public interface S2BPipe<S extends ServerChannel, D extends BusinessChannel<D>>
        extends ChannelTxPipe<S, D>, S2BNoReplyPipe<D> {
    S2BRequest<? extends S2BRequest, Object, Object> request();
}
