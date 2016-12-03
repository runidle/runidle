package io.runidle.async.channel.request;

import io.runidle.async.channel.MessageType;

public interface ChannelRequest<M extends ChannelRequest<M, T>, T> {
    /**
     * Set this message as Idempotent with key
     *
     * @param key
     * @return
     */
    M idem(Object key);

    /**
     * Set message type, it is required.
     *
     * @param messageType
     * @return
     */
    M messageType(MessageType messageType);

    /**
     * RequestContent
     *
     * @param requestContent
     * @return
     */
    M requestContent(T requestContent);

    /**
     * End and send this request message
     */
    void end();
}
