package io.runidle.async.channel;

import io.runidle.async.Runidle;
import io.runidle.async.channel.message.ChannelMessageContext;
import io.runidle.async.channel.pipe.B2BPipe;
import io.runidle.testing.benchmark.Benchmark;
import io.runidle.testing.unit.BaseUnitSpec;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusinessChannelBenchmark extends BaseUnitSpec {
    //Benchmark: ~750M/s
    @Test
    public void benchmarkSmallObject() {
        doBenchmark(() -> 1);
    }

    //Benchmark: ~750M/s
    @Test
    public void benchmarkBigObject() {
        BigObject bigObject = new BigObject();
        doBenchmark(() -> bigObject);
    }


    public void doBenchmark(ObjectCreator objectCreator) {
        BusinessChannel businessChannel1 = Runidle.createBusinessChannel(new ChannelConfig()
                .setName("business1").setQueueSize(8096));
        BusinessChannel businessChannel2 = Runidle.createBusinessChannel(new ChannelConfig()
                .setName("business2").setQueueSize(8096));
        businessChannel1.start();
        MessageType messageType = MessageType.newType();
        businessChannel2
                .handler(messageType, ChannelMessageContext::succeed)
                .start();

        B2BPipe<?, ?> pipe = businessChannel2.B2BPipe(businessChannel1);
        Benchmark.benchmark()
                .threads(4)
                .rounds(10)
                .concurrency(1000)
                .iterations(10000)
                .benchmarkTask((index, runnerContext) -> {
                    pipe.request()
                            .messageType(messageType)
                            .requestContent(objectCreator.create())
                            .onSuccess((requestContent, responseContent) -> runnerContext.done(index))
                            .onFail((requestContent, errMsg, cause) -> {
                            })
                            .end();
                }).start();

        businessChannel1.close();
        businessChannel2.close();
    }

    interface ObjectCreator {
        Object create();
    }

    static class BigObject {
        int a;
        boolean b;
        long c;
        String d;
        Map<String, String> data1 = new HashMap<>();
        List<String> data2 = new ArrayList<>();

        public BigObject() {
            for (int i = 0; i < 100; i++) {
                data1.put("Key_" + i, "Value_" + i);
                data2.add("Data2_" + i);
            }
        }
    }
}
