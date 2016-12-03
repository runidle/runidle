package io.runidle.async.channel.feature.timeout;

public class TimeoutException extends Exception {
    private final static String TIMEOUT_MSG = "Request Timeout";

    protected TimeoutException() {

    }

    protected TimeoutException(String msg) {
        super(msg);
    }

    public final static TimeoutException INSTANCE = new TimeoutException(TIMEOUT_MSG);
}
