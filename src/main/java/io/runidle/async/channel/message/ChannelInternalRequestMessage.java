package io.runidle.async.channel.message;

import io.runidle.async.channel.feature.ChannelMessageFeatures;
import io.runidle.async.channel.pipe.ChannelInternalPipe;
import io.runidle.async.channel.impl.ChannelInternalResponse;

public interface ChannelInternalRequestMessage<T, R>
        extends ChannelMessage<T, R>, ChannelMessageContext<R> {

    ChannelInternalPipe channelPipe();

    ChannelMessageFeatures<T, R> features();

    boolean finished();

    void finish();

    ChannelInternalRequestMessage<T, R> responded(boolean responded);

    boolean responded();

    ChannelInternalRequestMessage<T, R> noReply();

    ChannelInternalResponse<T, R> response();

}
