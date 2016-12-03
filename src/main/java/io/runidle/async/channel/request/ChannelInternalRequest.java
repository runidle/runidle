package io.runidle.async.channel.request;


public interface ChannelInternalRequest<M extends ChannelInternalRequest<M, T, R>, T, R>
        extends B2BRequest<M, T, R>, B2CRequest<M, T, R>, S2BRequest<M, T, R> {
}
