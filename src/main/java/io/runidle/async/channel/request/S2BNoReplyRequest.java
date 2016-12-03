package io.runidle.async.channel.request;

public interface S2BNoReplyRequest<M extends S2BNoReplyRequest<M, T>, T>
        extends ChannelNoReplyRequest<M, T> {
}
