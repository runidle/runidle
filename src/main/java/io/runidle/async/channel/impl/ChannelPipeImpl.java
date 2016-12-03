package io.runidle.async.channel.impl;

import io.runidle.async.channel.Channel;
import io.runidle.async.channel.pipe.ChannelPipeInterceptor;

public class ChannelPipeImpl extends AbstractChannelPipeImpl {

    public ChannelPipeImpl(Channel srcChannel, Channel destChannel,
                           ChannelPipeInterceptor interceptor) {
        super(srcChannel, destChannel, interceptor);
    }
}
