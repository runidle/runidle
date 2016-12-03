package io.runidle.async.example.interfaces;

import io.runidle.async.channel.BusinessChannel;

public interface ExampleChannel<C extends ExampleChannel<C>> extends BusinessChannel<C> {
}
