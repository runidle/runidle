package io.runidle.async.channel.impl;

import io.runidle.async.channel.BusinessChannel;
import io.runidle.async.channel.Channel;

public class FromBusinessChannelPipeIml extends ChannelPipeImpl {
    public FromBusinessChannelPipeIml(BusinessChannel srcChannel, Channel destChannel) {
        super(srcChannel, destChannel, new FromBusinessChannelInterceptorImpl());
    }
}
