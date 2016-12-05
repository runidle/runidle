package io.runidle.async.channel;

import io.runidle.async.channel.box.MessageBox;

import io.runidle.async.channel.message.ChannelInternalRequestMessage;
import io.runidle.async.channel.message.ChannelMessage;
import io.runidle.async.channel.request.DefaultMessageType;
import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public abstract class AbstractChannel<C extends AbstractChannel<C>> implements
        Channel<C> {
    @Getter
    @Accessors(fluent = true)
    private ChannelConfig channelConfig;
    private MessageBox messageBox;
    private volatile boolean started;

    protected AbstractChannel(ChannelConfig channelConfig, MessageBox messageBox) {
        Preconditions.checkState(StringUtils.isNotBlank(channelConfig.getName()));
        this.channelConfig = channelConfig;
        this.messageBox = messageBox;
    }

    @Override
    public void write(ChannelMessage message) {
        this.messageBox.put(message.messageType(), message);
    }

    @Override
    public <T, R> C handler(MessageType messageType, ChannelHandler<T, R> channelHandler) {
        if (started) {
            log.warn("Set handler must be before started");
        }
        this.messageBox.<ChannelMessage<T, R>>handler(messageType,
                (messageType1, m) -> {
                    if (m instanceof ChannelInternalRequestMessage) {
                        ((ChannelInternalRequestMessage<T, R>) m).channelPipe()
                                .acceptor().handleRequest((ChannelInternalRequestMessage<T, R>) m, channelHandler);
                    } else {
                        channelHandler.handle(m);
                    }
                }
        );
        return (C) this;
    }

    @Override
    public <T, R> C handler(MessageType messageType, ChannelHandler2<T, R> channelHandler) {
        return this.<T, R>handler(messageType, requestMessage -> {
            channelHandler.handle(requestMessage.messageContext(), requestMessage.requestContent());
        });
    }

    @Override
    public void write(MessageType messageType, ChannelMessage message) {
        this.messageBox.put(messageType, message);
    }

    public C start() {
        if (this.started) return (C) this;
        this.started = true;
        this.messageBox.<ChannelInternalRequestMessage>handler(DefaultMessageType.Response,
                (messageType1, message) -> {
                    message.channelPipe().sender().handleResponse(message);
                });

        this.messageBox.start();
        return (C) this;
    }

    public void close() {
        if (!this.started) return;
        this.started = false;
        this.messageBox.close();
    }
}
