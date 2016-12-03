package io.runidle.async.channel.pipe;

import io.runidle.async.channel.scheduler.ChannelSchedule;

public interface ChannelPipe {
    ChannelSchedule schedule();
}
