package io.runidle.async.channel.box;

import io.runidle.async.channel.TestMessageType;
import io.runidle.testing.benchmark.Benchmark;
import io.runidle.testing.unit.BaseUnitSpec;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class MessageBoxBenchmark extends BaseUnitSpec {

    protected void doBenchmark(MessageBoxCreator messageBoxCreator, int typeCount) {
        for (int k = 0; k < 5; k++) {
            MessageBox messageBox = messageBoxCreator.create();
            final int total = 10000000;
            Blocker blocker = new Blocker();
            final AtomicInteger counter = new AtomicInteger();
            for (int i = 0; i < typeCount; i++) {
                messageBox.handler(TestMessageType.values()[i], (messageType, message) -> {
                    blocker.endIf(counter.incrementAndGet() == total);
                });
            }
            messageBox.start();

            Random random = new Random();
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < total; i++) {
                messageBox.put(TestMessageType.values()[random.nextInt(typeCount)], 1);
            }
            blocker.awaitEnd();
            log.info("Spent time: " + (System.currentTimeMillis() - startTime));
        }
    }

    interface MessageBoxCreator {
        MessageBox create();
    }
}
