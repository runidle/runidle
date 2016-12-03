package io.runidle.async.channel.request;

import io.runidle.async.channel.MessageType;

public enum DefaultMessageType implements MessageType {
    Response, Timeout, Schedule;
}
