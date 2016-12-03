package io.runidle.async.channel.feature.idem;

import io.runidle.async.channel.impl.ChannelMessageImpl;
import io.runidle.async.channel.message.ChannelInternalRequestMessage;
import io.runidle.async.channel.pipe.ChannelInternalPipe;
import io.runidle.testing.unit.BaseUnitSpec;
import org.mockito.Mock;
import org.testng.annotations.Test;

import static org.testng.Assert.*;


public class IdemTest extends BaseUnitSpec {

    @Mock
    private ChannelInternalPipe channelInternalPipe;

    @Test
    public void testIdem() {
        IdempotentHandler idempotentHandler = new IdempotentHandler();
        ChannelInternalRequestMessage primaryMessage = new ChannelMessageImpl(channelInternalPipe);
        primaryMessage.features().idem("1");
        idempotentHandler.handle(primaryMessage);
        assertTrue(primaryMessage.features().idem().primary());

        ChannelInternalRequestMessage subMessage1 = new ChannelMessageImpl(channelInternalPipe);
        ChannelInternalRequestMessage subMessage2 = new ChannelMessageImpl(channelInternalPipe);
        subMessage1.features().idem("1");
        subMessage2.features().idem("1");

        assertTrue(subMessage1.features().idem().primary());
        assertTrue(subMessage2.features().idem().primary());

        idempotentHandler.handle(subMessage1);
        idempotentHandler.handle(subMessage2);
        assertTrue(primaryMessage.features().idem().primary());
        assertFalse(subMessage1.features().idem().primary());
        assertFalse(subMessage2.features().idem().primary());
        assertTrue(primaryMessage.features().idem().followBy(subMessage1));
        assertTrue(primaryMessage.features().idem().followBy(subMessage2));

        idempotentHandler.end(primaryMessage);

        assertTrue(idempotentHandler.channelMessages.isEmpty());
    }
}