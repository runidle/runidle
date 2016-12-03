package io.runidle.async.channel;

import io.runidle.async.channel.pipe.B2BNoReplyPipe;
import io.runidle.async.channel.pipe.B2BPipe;
import io.runidle.async.channel.pipe.S2BNoReplyPipe;
import io.runidle.async.channel.pipe.S2BPipe;

public interface BusinessChannel<C extends BusinessChannel<C>> extends Channel<C> {
    B2BNoReplyPipe<C> B2BNoReplyPipe();

    B2BPipe<?, C> B2BPipe(BusinessChannel fromChannel);

    S2BNoReplyPipe<C> S2BNoReplyPipe();

    S2BPipe<?, C> S2BPipe(ServerChannel fromChannel);
}
