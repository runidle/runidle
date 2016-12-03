package io.runidle.async.event.disruptor;

import io.runidle.async.event.IEventClass;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;


public class DisruptorEventBusConfig extends HashMap<IEventClass, DisruptorEventBusConfig.DisruptorConfig> {

    public DisruptorConfig config(IEventClass eventClass) {
        return super.get(eventClass);
    }

    @Data
    @Accessors(chain = true)
    public static class DisruptorConfig {
        private int bufferSize = 1 << 16;
    }
}
