package io.runidle.async.channel.feature.retry;

import io.runidle.async.channel.feature.ChannelMessageFeature;
import io.runidle.async.channel.message.ChannelInternalRequestMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Retry extends ChannelMessageFeature {
    private int retryCount = 0;
    private long intervalMs = -1;

    public Retry(int retryCount, long intervalMs, ChannelInternalRequestMessage channelMessage) {
        super(channelMessage);
        this.retryCount = retryCount;
        this.intervalMs = intervalMs;
    }

    public boolean retry() {
        if (retryCount <= 0) return false;
        retryCount = retryCount - 1;

        this.channelMessage().channelPipe().schedule()
                .delay(this.intervalMs)
                .onTime(() -> {
                    channelMessage().channelPipe().sender().send(channelMessage());
                })
                .start();
        return true;
    }
}
