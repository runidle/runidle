package io.runidle.async.example.impl;

import io.runidle.async.channel.BusinessChannelImpl;
import io.runidle.async.channel.ChannelConfig;
import io.runidle.async.channel.message.ChannelMessageContext;
import io.runidle.async.example.interfaces.ExampleChannel;
import io.runidle.async.example.interfaces.ExampleMessageType;

public class ExmapleChannelImpl extends BusinessChannelImpl<ExmapleChannelImpl>
        implements ExampleChannel<ExmapleChannelImpl> {
    public ExmapleChannelImpl(ChannelConfig channelConfig) {
        super(channelConfig);
        this.handler(ExampleMessageType.GET, this::handleGet)
                .handler(ExampleMessageType.SAVE, this::handleSave)
                .start();
    }

    void handleGet(ChannelMessageContext<String> messageContext, Integer requestContent) {
        messageContext.succeed("OK");
    }

    void handleSave(ChannelMessageContext<String> messageContext, Integer requestContent) {
        messageContext.succeed("OK");
    }
}
