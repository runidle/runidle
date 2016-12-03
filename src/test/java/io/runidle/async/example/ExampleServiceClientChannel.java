package io.runidle.async.example;

import io.runidle.async.channel.ChannelConfig;
import io.runidle.async.channel.ServerChannelImpl;
import io.runidle.async.example.impl.ExampleService;
import io.runidle.async.example.interfaces.ExampleChannelPipe;

public class ExampleServiceClientChannel extends ServerChannelImpl {
    private ExampleChannelPipe exampleChannelPipe;

    public ExampleServiceClientChannel(ChannelConfig channelConfig, ExampleService exampleService) {
        super(channelConfig);
        this.exampleChannelPipe = exampleService.channelPipe(this);
    }

    private void doGet() {
        this.exampleChannelPipe
                .get().key(1).idem(1).timeout(3000)
                //.requestContent(1)
                .onSuccess((requestContent, responseContent) -> {})
                .onFail((requestContent, errMsg, cause) -> {})
                .end();
    }
}
