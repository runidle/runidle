package io.runidle.async.channel.feature;

import io.runidle.async.channel.message.ChannelInternalRequestMessage;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public abstract class ChannelMessageFeature {
    private ChannelInternalRequestMessage channelMessage;

    protected ChannelMessageFeature(ChannelInternalRequestMessage channelMessage) {
        this.channelMessage = channelMessage;
    }
}
