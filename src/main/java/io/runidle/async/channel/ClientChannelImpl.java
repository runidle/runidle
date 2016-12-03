package io.runidle.async.channel;

import io.runidle.async.channel.box.DirectMessageBox;
import io.runidle.async.channel.impl.FromBusinessChannelPipeIml;
import io.runidle.async.channel.impl.FromBusinessNoReplyPipeImpl;
import io.runidle.async.channel.pipe.B2CNoReplyPipe;
import io.runidle.async.channel.pipe.B2CPipe;

public class ClientChannelImpl<C extends ClientChannelImpl<C>>
        extends AbstractChannel<C> implements ClientChannel<C> {
    public ClientChannelImpl(ChannelConfig channelConfig) {
        super(channelConfig, new DirectMessageBox(channelConfig.getName()));
    }

    @Override
    public B2CNoReplyPipe<C> B2CNoReplyPipe() {
        return new FromBusinessNoReplyPipeImpl(this);
    }

    @Override
    public B2CPipe<?, C> B2CPipe(BusinessChannel fromChannel) {
        return new FromBusinessChannelPipeIml(fromChannel, this);
    }
}