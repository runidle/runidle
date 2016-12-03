package io.runidle.async.channel.impl;

import io.runidle.async.channel.Channel;
import io.runidle.async.channel.pipe.B2BNoReplyPipe;
import io.runidle.async.channel.pipe.B2CNoReplyPipe;
import io.runidle.async.channel.pipe.ChannelPipeInterceptor;
import io.runidle.async.channel.pipe.S2BNoReplyPipe;

public class NoReplyChannelPipeImpl extends AbstractChannelPipeImpl implements
        B2BNoReplyPipe, B2CNoReplyPipe, S2BNoReplyPipe {
    public NoReplyChannelPipeImpl(Channel destChannel, ChannelPipeInterceptor interceptor) {
        super(null, destChannel, interceptor);
    }

    @Override
    public boolean hasReply() {
        return false;
    }
}
