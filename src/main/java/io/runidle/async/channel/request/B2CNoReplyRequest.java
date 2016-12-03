package io.runidle.async.channel.request;

public interface B2CNoReplyRequest<M extends B2CNoReplyRequest<M, T>, T>
        extends ChannelNoReplyRequest<M, T> {
}
