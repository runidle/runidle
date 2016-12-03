package io.runidle.async.channel.request;

public interface ChannelNoReplyRequest<M extends ChannelNoReplyRequest<M, T>, T>
        extends ChannelRequest<M, T> {
}
