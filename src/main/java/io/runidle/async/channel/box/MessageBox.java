package io.runidle.async.channel.box;

import io.runidle.async.channel.MessageType;
import lombok.ToString;

public interface MessageBox {
    MessageBox put(MessageType messageType, Object message);

    MessageBoxId messageBoxId();

    <T> MessageBox handler(MessageType messageType, MessageBoxHandler<T> handler);

    MessageBox start();

    void close();

    @ToString
    class MessageEvent {
        MessageType messageType;
        Object message;
        int handled;

        public MessageEvent(){}

        public MessageEvent(MessageType messageType, Object message) {
            this.messageType = messageType;
            this.message = message;
        }

        void reset() {
            messageType = null;
            message = null;
        }

        void handled() {
            this.handled = this.handled + 1;
        }
    }

    interface MessageBoxHandler<T> {
        void handle(MessageType messageType, T message);
    }

    class HandlerChain {
        MessageType messageType;
        MessageBoxHandler handler;
        HandlerChain next;

        public HandlerChain(MessageType messageType, MessageBoxHandler handler) {
            this.messageType = messageType;
            this.handler = handler;
        }

        public boolean handleEvent(MessageEvent event) {
            HandlerChain found = find(event.messageType);
            if (found == null) return false;
            found.handler.handle(event.messageType, event.message);
            return true;
        }

        public void addHandler(MessageType messageType, MessageBoxHandler handler) {
            HandlerChain found = this.find(messageType);
            if (found != null) {
                found.handler = handler;
                return;
            }
            if (this.handler == null) {
                this.handler = handler;
                this.messageType = messageType;
            } else this.addToNext(messageType, handler);
        }

        private void addToNext(MessageType messageType, MessageBoxHandler handler) {
            if (next == null) next = new HandlerChain(messageType, handler);
            else next.addToNext(messageType, handler);
        }

        private HandlerChain find(MessageType messageType) {
            HandlerChain handlerChain = this;
            while (true) {
                if (handlerChain.messageType == messageType) return handlerChain;
                handlerChain = handlerChain.next;
                if (handlerChain == null) return null;
            }
        }
    }
}
