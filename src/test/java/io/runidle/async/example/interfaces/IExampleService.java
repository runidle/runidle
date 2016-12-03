package io.runidle.async.example.interfaces;


import io.runidle.async.channel.ServerChannel;

public interface IExampleService {
    ExampleChannelPipe channelPipe(ServerChannel srcChannel);
}
