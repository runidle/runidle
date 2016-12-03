package io.runidle.async.example.interfaces;

import io.runidle.async.channel.pipe.S2BPipe;

public interface ExampleChannelPipe extends S2BPipe {
    <M extends ExampleGet<M>> M get();

    <M extends ExampleSave<M>> M save();
}
