package io.runidle.async.example.impl;

import io.runidle.async.channel.ServerChannel;
import io.runidle.async.channel.impl.FromServerChannelPipeImpl;
import io.runidle.async.example.interfaces.ExampleChannel;
import io.runidle.async.example.interfaces.ExampleChannelPipe;
import io.runidle.async.example.interfaces.ExampleGet;
import io.runidle.async.example.interfaces.ExampleSave;

public class ExampleChannelPipeImpl extends
        FromServerChannelPipeImpl implements ExampleChannelPipe {
    public ExampleChannelPipeImpl(ServerChannel srcChannel, ExampleChannel destChannel) {
        super(srcChannel, destChannel);
    }

    @Override
    public ExampleGet get() {
        return ExampleGet.create(this);
    }

    @Override
    public ExampleSave save() {
        return ExampleSave.create(this);
    }
}
