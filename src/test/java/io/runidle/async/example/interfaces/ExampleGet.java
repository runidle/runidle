package io.runidle.async.example.interfaces;

import io.runidle.async.channel.impl.ChannelMessageImpl;
import io.runidle.async.channel.pipe.ChannelPipeContext;
import io.runidle.async.channel.request.S2BRequest;
import lombok.Setter;
import lombok.experimental.Accessors;

public interface ExampleGet<M extends ExampleGet<M>>
        extends S2BRequest<M, Integer, String> {
    ExampleGet<M> key(Integer key);

    static <M extends ExampleGet<M>> M create(ChannelPipeContext channelPipeContext) {
        return (M) new ExampleGetMessage(channelPipeContext);
    }
}

@Setter
@Accessors(fluent = true)
class ExampleGetMessage<M extends ExampleGetMessage<M>>
        extends ChannelMessageImpl<M, Integer, String>
        implements ExampleGet<M> {

    private Integer key;

    protected ExampleGetMessage(ChannelPipeContext channelPipeContext) {
        super(channelPipeContext);
        this.messageType(ExampleMessageType.GET);
    }

    @Override
    public void end() {
        this.requestContent(this.key);
        super.end();
    }

}
