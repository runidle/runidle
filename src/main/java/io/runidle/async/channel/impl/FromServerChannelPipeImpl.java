package io.runidle.async.channel.impl;

import io.runidle.async.channel.Channel;
import io.runidle.async.channel.ServerChannel;

public class FromServerChannelPipeImpl extends ChannelPipeImpl {
    public FromServerChannelPipeImpl(ServerChannel srcChannel, Channel destChannel) {
        super(srcChannel, destChannel, new FromServerChannelInterceptorImpl());
    }
}
