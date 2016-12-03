package io.runidle.async.channel.message;

public interface ChannelMessageContext<R> {
    void fail(String errMsg, Throwable ex);

    void succeed(R responseContent);
}
