package io.runidle.async.channel.feature.retry;

import io.runidle.async.channel.impl.ChannelMessageImpl;
import io.runidle.async.channel.message.ChannelInternalRequestMessage;
import io.runidle.async.channel.pipe.ChannelInternalPipe;
import io.runidle.async.channel.scheduler.ChannelSchedule;
import io.runidle.async.channel.scheduler.ChannelScheduler;
import io.runidle.testing.unit.BaseUnitSpec;
import org.mockito.Mock;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

public class RetryTest extends BaseUnitSpec {
    @Mock
    private ChannelInternalPipe channelInternalPipe;
    @Mock
    private ChannelScheduler channelScheduler;

    @Test
    public void testRetry() {
        when(channelInternalPipe.channelPipe()).thenReturn(channelInternalPipe);
        ChannelInternalRequestMessage primaryMessage = new ChannelMessageImpl(channelInternalPipe);
        ChannelSchedule channelSchedule = spy(new ChannelSchedule(channelScheduler));
        when(channelInternalPipe.schedule()).thenReturn(channelSchedule);
        primaryMessage.features().retry(3, 1);
        while (primaryMessage.features().retry().retry()) {
        }
        verify(channelSchedule, times(3)).start();
    }
}