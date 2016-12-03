package io.runidle.async.channel;

public interface MessageType {

    static MessageType newType() {
        return new MessageType() {
        };
    }
}
