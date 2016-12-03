package io.runidle.async.channel.box;

import io.netty.util.concurrent.DefaultThreadFactory;
import org.testng.annotations.Test;

public class DisruptorMessageBoxBenchmark extends MessageBoxBenchmark {
    @Test
    public void benchmarkOne() {
        doBenchmark(1, 1024);
    }

    @Test
    public void benchmarkFourTypes() {
        doBenchmark(4, 1024);
    }

    @Test
    public void benchmarkSmallQueue() {
        doBenchmark(1, 256);
    }

    @Test
    public void benchmarkMidQueue() {
        doBenchmark(1, 2048);
    }

    @Test
    public void benchmarkLargeQueue() {
        doBenchmark(1, 8192);
    }


    private void doBenchmark(int typeCount, int queueSize) {
        super.doBenchmark(() -> new DisruptorMessageBox("testBox", queueSize,
                1, new DefaultThreadFactory("Test")), typeCount);
    }
}
