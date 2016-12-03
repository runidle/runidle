package io.runidle.async.event;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class EventMessage {
    protected IEventClass eventClass;
    protected IEventType eventType;

    protected Object eventBody;

    protected EventMessage(){}
}
