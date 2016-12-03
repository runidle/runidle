package io.runidle.async.channel.impl;

import io.runidle.async.channel.Channel;

public class FromServerNoReplyPipeImpl extends NoReplyChannelPipeImpl {
    public FromServerNoReplyPipeImpl(Channel destChannel) {
        super(destChannel, new FromServerChannelInterceptorImpl());
    }
}
