package io.runidle.async.channel;

import io.runidle.async.Runidle;
import io.runidle.async.channel.pipe.B2BNoReplyPipe;
import io.runidle.async.channel.pipe.B2BPipe;
import io.runidle.async.channel.pipe.S2BNoReplyPipe;
import io.runidle.async.channel.pipe.S2BPipe;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class BusinessChannelTest extends ChannelTest {
    @Test
    public void testB2BNoReply() {
        BusinessChannel businessChannel = Runidle.createBusinessChannel(new ChannelConfig().setName("business1"));
        B2BNoReplyPipe<?> b2BNoReplyPipe = businessChannel.B2BNoReplyPipe();

        super.testNoReply(b2BNoReplyPipe);
    }

    @Test
    public void testS2BNoReply() {
        BusinessChannel businessChannel = Runidle.createBusinessChannel(new ChannelConfig().setName("business1"));
        S2BNoReplyPipe<?> s2BNoReplyPipe = businessChannel.S2BNoReplyPipe();

        super.testNoReply(s2BNoReplyPipe);
    }

    @Test
    public void testB2BPipe() {
        BusinessChannel businessChannel1 = Runidle.createBusinessChannel(new ChannelConfig().setName("business1"));
        BusinessChannel businessChannel2 = Runidle.createBusinessChannel(new ChannelConfig().setName("business2"));
        B2BPipe<?, ?> b2BPipe = businessChannel2.B2BPipe(businessChannel1);

        super.testCommunication(b2BPipe);
    }

    @Test
    public void testS2BPipe() {
        ServerChannel serverChannel = Runidle.createServerChannel(new ChannelConfig().setName("server"));
        BusinessChannel businessChannel2 = Runidle.createBusinessChannel(new ChannelConfig().setName("business2"));
        S2BPipe<?, ?> s2BPipe = businessChannel2.S2BPipe(serverChannel);

        super.testCommunication(s2BPipe);
    }

    @Test
    public void testHandleInSingleThread() {
        BusinessChannel businessChannel = Runidle.createBusinessChannel(new ChannelConfig().setName("business1"));
        MessageType testType = MessageType.newType();
        AtomicLong threadId = new AtomicLong(-1);
        AtomicInteger counter = new AtomicInteger();
        int testCount = 100000;
        Blocker blocker = new Blocker();
        businessChannel.handler(testType, (messageContext, requestContent) -> {
            long currentId = Thread.currentThread().getId();
            long oldId = threadId.getAndSet(currentId);
            if (oldId != -1) {
                blocker.assertTrue(currentId == oldId);
            }
            blocker.endIf(counter.incrementAndGet() == testCount);
        }).start();

        B2BNoReplyPipe<?> pipe = businessChannel.B2BNoReplyPipe();

        for (int i = 0; i < testCount; i++) {
            pipe.noReply().messageType(testType).end();
        }
        blocker.awaitEnd();
        businessChannel.close();
    }

    @Test
    public void testB2BPipeTimeout() {
        BusinessChannel businessChannel1 = Runidle.createBusinessChannel(new ChannelConfig().setName("business1"));
        BusinessChannel businessChannel2 = Runidle.createBusinessChannel(new ChannelConfig().setName("business2"));
        businessChannel1.start();
        MessageType messageType = MessageType.newType();
        businessChannel2
                .handler(messageType, (messageContext, requestContent) -> {

                })
                .start();
        B2BPipe<?, ?> b2BPipe = businessChannel2.B2BPipe(businessChannel1);

        Blocker blocker = new Blocker();
        b2BPipe.request()
                .messageType(messageType)
                .onSuccess((requestContent, responseContent) -> {

                })
                .onFail((requestContent, errMsg, cause) -> {
                    blocker.end();
                })
                .timeout(100)
                .end();

        blocker.awaitEnd();
        businessChannel1.close();
        businessChannel2.close();
    }


    public void testB2BPipeIdem() {

    }

    public void testB2BNoReplyIdem() {

    }

    public void testS2BPipeTimeout() {

    }

    public void testS2BPipeIdem() {

    }

    public void testS2BNoReplyIdem() {

    }
}
