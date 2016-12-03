package io.runidle.async.channel.scheduler;

import io.runidle.async.utils.ChannelTimer;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;

public class ChannelScheduler {
    private static class InstanceHolder {
        private final static ChannelScheduler instance = new ChannelScheduler();
        private final static HashedWheelTimer timer = ChannelTimer.timer();
    }

    private ChannelScheduler() {
    }

    public static ChannelSchedule schedule() {
        return new ChannelSchedule(InstanceHolder.instance);
    }

    protected Timeout doSchedule(ChannelSchedule schedule) {
        return InstanceHolder.timer.newTimeout(timeout -> {
            schedule.finish();
        }, schedule.delay(), schedule.timeUnit());
    }
}
