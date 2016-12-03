package io.runidle.async.channel;

import io.runidle.async.channel.box.DisruptorMessageBox;
import io.runidle.async.channel.impl.FromBusinessChannelPipeIml;
import io.runidle.async.channel.impl.FromBusinessNoReplyPipeImpl;
import io.runidle.async.channel.impl.FromServerChannelPipeImpl;
import io.runidle.async.channel.impl.FromServerNoReplyPipeImpl;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.runidle.async.channel.pipe.B2BNoReplyPipe;
import io.runidle.async.channel.pipe.B2BPipe;
import io.runidle.async.channel.pipe.S2BNoReplyPipe;
import io.runidle.async.channel.pipe.S2BPipe;
import lombok.extern.slf4j.Slf4j;

/**
 * BusinessChannelImpl
 * Make all messages for this channel be handled in the same thread
 */
@Slf4j
public class BusinessChannelImpl<C extends BusinessChannelImpl<C>>
        extends AbstractChannel<C> implements BusinessChannel<C> {
    public BusinessChannelImpl(ChannelConfig channelConfig) {
        super(channelConfig,
                new DisruptorMessageBox(channelConfig.getName() + "_box",
                        channelConfig.getQueueSize(), 1,
                        new DefaultThreadFactory(channelConfig.getName())));
    }

    @Override
    public B2BNoReplyPipe<C> B2BNoReplyPipe( ) {
        return new FromBusinessNoReplyPipeImpl(this);
    }

    @Override
    public B2BPipe<?, C> B2BPipe(BusinessChannel fromChannel) {
        return new FromBusinessChannelPipeIml(fromChannel, this);
    }

    @Override
    public S2BNoReplyPipe<C> S2BNoReplyPipe() {
        return new FromServerNoReplyPipeImpl(this);
    }

    @Override
    public S2BPipe<?, C> S2BPipe(ServerChannel fromChannel) {
        return new FromServerChannelPipeImpl(fromChannel,this);
    }

}
