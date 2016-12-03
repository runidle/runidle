package io.runidle.async.event;

import io.runidle.async.event.disruptor.DisruptorEventBusConfig;
import org.testng.annotations.Test;


public class DisruptorEventBusTest extends EventBusTest {

    @Test
    public void testDisruptorBus1() {
        DisruptorEventBusConfig config = new DisruptorEventBusConfig();
        config.put(EventClass.Event1, new DisruptorEventBusConfig.DisruptorConfig());
        config.put(EventClass.Event2, new DisruptorEventBusConfig.DisruptorConfig());
        config.put(EventClass.Event3, new DisruptorEventBusConfig.DisruptorConfig());

        //base tests
        this.testBus(config, 1, 1, 1, 1, 10);
//        this.testBus(config, 3, 1, 1, 1, 10);
//        this.testBus(config, 1, 3, 1, 1, 10);
        this.testBus(config, 1, 1, 3, 1, 10);
//        this.testBus(config, 1, 1, 1, 3, 10);
//        this.testBus(config, 3, 3, 3, 3, 10);
//        //performance tests
        //this.testBus(config, 1, 1, 1, 1, 1000000);
//
//        this.testBus(config, 3, 1, 1, 1, 1000000);
//
//        this.testBus(config, 1, 3, 1, 1, 200000);
//
//        this.testBus(config, 3, 3, 3, 3, 10000);
    }

    @Test
    public void testDisruptorBus2() {
        DisruptorEventBusConfig config = new DisruptorEventBusConfig();
        config.put(EventClass.Event1, new DisruptorEventBusConfig.DisruptorConfig());
        config.put(EventClass.Event2, new DisruptorEventBusConfig.DisruptorConfig());

//        //performance tests
        this.testBus(config, 1, 1, 1, 2, 10000000);
    }

    @Test
    public void testDisruptorBus3() {
        DisruptorEventBusConfig config = new DisruptorEventBusConfig();
        config.put(EventClass.Event1, new DisruptorEventBusConfig.DisruptorConfig());
        //config.put(EventType.Event2, new DisruptorEventBusConfig.DisruptorConfig());

//        //performance tests
        this.testBus(config, 1, 1, 1, 1, 10000000);
    }
}