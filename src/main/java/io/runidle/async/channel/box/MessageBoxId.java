package io.runidle.async.channel.box;

import lombok.ToString;

@ToString
public class MessageBoxId {
    private String name;

    public MessageBoxId(String name) {
        this.name = name;
    }
}
