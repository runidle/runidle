package io.runidle.async.example.impl;

import io.runidle.async.channel.ChannelConfig;
import io.runidle.async.channel.ServerChannel;
import io.runidle.async.example.interfaces.ExampleChannel;
import io.runidle.async.example.interfaces.ExampleChannelPipe;
import io.runidle.async.example.interfaces.IExampleService;

public class ExampleService implements IExampleService {
    private ExampleChannel exampleChannel = new ExmapleChannelImpl(new ChannelConfig().setName(""));

    @Override
    public ExampleChannelPipe channelPipe(ServerChannel srcChannel) {
        return new ExampleChannelPipeImpl(srcChannel, exampleChannel);
    }
}
