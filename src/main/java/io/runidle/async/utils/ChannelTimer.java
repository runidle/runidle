package io.runidle.async.utils;

import io.netty.util.HashedWheelTimer;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ChannelTimer {
    private static HashedWheelTimer timer;

    public synchronized static HashedWheelTimer timer() {
        if (timer == null)
            timer = new HashedWheelTimer(
                    new DefaultThreadFactory("Channel-Timer"),
                    10, TimeUnit.MILLISECONDS);
        timer.start();
        log.info("Channel-Timer started.");
        return timer;
    }

}
