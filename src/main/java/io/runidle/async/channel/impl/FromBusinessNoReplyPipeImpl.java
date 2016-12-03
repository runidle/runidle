package io.runidle.async.channel.impl;

import io.runidle.async.channel.Channel;

public class FromBusinessNoReplyPipeImpl extends NoReplyChannelPipeImpl {
    public FromBusinessNoReplyPipeImpl(Channel destChannel) {
        super(destChannel, new FromBusinessChannelInterceptorImpl());
    }
}
