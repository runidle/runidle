package io.runidle.async.channel.feature.timeout;

import io.runidle.async.channel.feature.ChannelMessageFeature;
import io.runidle.async.channel.message.ChannelInternalRequestMessage;
import io.runidle.async.channel.scheduler.ChannelSchedule;

public class Timeout extends ChannelMessageFeature {
    private ChannelSchedule timeoutSchedule;

    public Timeout(long timeoutMs, ChannelInternalRequestMessage channelMessage) {
        super(channelMessage);

        this.timeoutSchedule = channelMessage
                .channelPipe()
                .schedule()
                .delay(timeoutMs)
                .onTime(() -> {
                    channelMessage.channelPipe().acceptor().responseTimeout(channelMessage);
                });
    }

    public void start() {
        this.timeoutSchedule.start();
    }

    public void cancel() {
        this.timeoutSchedule.cancel();
    }

    public boolean isDone() {
        return this.timeoutSchedule != null
                && this.timeoutSchedule.isDone();
    }
}
