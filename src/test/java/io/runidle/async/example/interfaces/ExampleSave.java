package io.runidle.async.example.interfaces;

import io.runidle.async.channel.impl.ChannelMessageImpl;
import io.runidle.async.channel.pipe.ChannelPipeContext;
import io.runidle.async.channel.request.B2CRequest;
import lombok.Setter;
import lombok.experimental.Accessors;

public interface ExampleSave<M extends ExampleSave<M>>
        extends B2CRequest<M, String, Boolean> {
    ExampleSave<M> data(String data);

    static <M extends ExampleSave<M>> M create(ChannelPipeContext channelPipeContext) {
        return (M) new ExampleSaveMessage(channelPipeContext);
    }
}

@Setter
@Accessors(fluent = true)
class ExampleSaveMessage<M extends ExampleSaveMessage<M>>
        extends ChannelMessageImpl<M, String, Boolean>
        implements ExampleSave<M> {
    private String data;

    protected ExampleSaveMessage(ChannelPipeContext channelPipeContext) {
        super(channelPipeContext);
        this.messageType(ExampleMessageType.SAVE);
    }

    @Override
    public void end() {
        this.requestContent(this.data);
        super.end();
    }
}
