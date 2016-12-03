package io.runidle.async.channel.box;

import io.runidle.async.channel.MessageType;
import lombok.Getter;
import lombok.experimental.Accessors;

public class DirectMessageBox implements MessageBox {
    @Getter
    @Accessors(fluent = true)
    private MessageBoxId messageBoxId;
    private HandlerChain handlerChain;

    public DirectMessageBox(String name) {
        this.messageBoxId = new MessageBoxId(name);
    }

    @Override
    public MessageBox put(MessageType messageType, Object message) {
        this.handlerChain.handleEvent(new MessageEvent(messageType, message));
        return this;
    }

    @Override
    public <T> MessageBox handler(MessageType messageType, MessageBoxHandler<T> handler) {
        if (handlerChain != null) {
            handlerChain.addHandler(messageType, handler);
            return this;
        }
        handlerChain = new HandlerChain(messageType, handler);
        return this;
    }

    @Override
    public DirectMessageBox start() {
        return this;
    }

    @Override
    public void close() {

    }
}
