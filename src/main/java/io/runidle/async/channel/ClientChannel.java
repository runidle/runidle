package io.runidle.async.channel;


import io.runidle.async.channel.pipe.B2CNoReplyPipe;
import io.runidle.async.channel.pipe.B2CPipe;

public interface ClientChannel<C extends ClientChannel<C>> extends Channel<C> {
    B2CNoReplyPipe<C> B2CNoReplyPipe();

    B2CPipe<?, C> B2CPipe(BusinessChannel fromChannel);
}
