package io.runidle.async.channel;

import io.runidle.async.Runidle;
import io.runidle.async.channel.pipe.B2CNoReplyPipe;
import io.runidle.async.channel.pipe.B2CPipe;
import org.testng.annotations.Test;

public class ClientChannelTest extends ChannelTest {
    @Test
    public void testB2CNoReply() {
        ClientChannel channel = Runidle.createClientChannel(new ChannelConfig().setName("client"));
        B2CNoReplyPipe<?> noReplyPipe = channel.B2CNoReplyPipe();

        super.testNoReply(noReplyPipe);
    }

    @Test
    public void testB2CPipe() {
        BusinessChannel businessChannel = Runidle.createBusinessChannel(new ChannelConfig().setName("business1"));
        ClientChannel clientChannel = Runidle.createClientChannel(new ChannelConfig().setName("client"));
        B2CPipe<?, ?> pipe = clientChannel.B2CPipe(businessChannel);

        super.testCommunication(pipe);
    }

    public void testB2CPipeTimeout() {

    }

    public void testB2CPipeRetry() {

    }

    public void testB2CPipeIdem() {

    }

    public void testB2CNoReplyIdem() {

    }
}
