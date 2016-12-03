package io.runidle.async.channel.scheduler;

import io.netty.util.Timeout;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.concurrent.TimeUnit;

public class ChannelSchedule {
    @Setter
    @Accessors(fluent = true)
    @Getter(AccessLevel.PROTECTED)
    private long delay = 0;
    @Setter
    @Accessors(fluent = true)
    @Getter(AccessLevel.PROTECTED)
    private long interval = -1;
    @Setter
    @Accessors(fluent = true)
    @Getter(AccessLevel.PROTECTED)
    private TimeUnit timeUnit = TimeUnit.MILLISECONDS;
    @Setter
    @Accessors(fluent = true)
    private OnTime onTime;
    @Setter
    @Accessors(fluent = true)
    private AfterCancel afterCancel;
    @Getter
    @Accessors(fluent = true)
    private boolean isCanceled;
    @Getter
    @Accessors(fluent = true)
    private boolean isDone;

    private Timeout timeout;

    private ChannelScheduler scheduler;

    public ChannelSchedule(ChannelScheduler scheduler) {
        this.scheduler = scheduler;
    }

    protected void finish() {
        if (this.isCanceled) return;
        if (this.interval < 0)
            this.isDone = true;
        if (this.onTime != null)
            this.onTime.doOnTime();
        if (this.interval > 0) {
            this.start();
        }
    }

    public ChannelSchedule start() {
        this.timeout = this.scheduler.doSchedule(this);
        return this;
    }

    public void cancel() {
        if (this.isDone || this.isCanceled) return;
        if (this.timeout != null
                && !this.timeout.isCancelled()
                && !this.timeout.isExpired()) {
            this.timeout.cancel();
            this.isCanceled = true;
            if (this.afterCancel != null)
                this.afterCancel.doAfterCancel();
        }
    }

    public interface OnTime {
        void doOnTime();
    }

    public interface AfterCancel {
        void doAfterCancel();
    }
}
