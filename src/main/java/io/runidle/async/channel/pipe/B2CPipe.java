package io.runidle.async.channel.pipe;

import io.runidle.async.channel.BusinessChannel;
import io.runidle.async.channel.ClientChannel;
import io.runidle.async.channel.request.B2CRequest;

public interface B2CPipe<S extends BusinessChannel<S>, D extends ClientChannel>
        extends ChannelTxPipe<S, D>, B2CNoReplyPipe<D> {
    B2CRequest<? extends B2CRequest, Object, Object> request();
}
