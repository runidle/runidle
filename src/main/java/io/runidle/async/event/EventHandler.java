package io.runidle.async.event;

public interface EventHandler<E> {
    void handle(E eventBody);
}
