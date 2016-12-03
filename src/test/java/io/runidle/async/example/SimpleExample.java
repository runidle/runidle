package io.runidle.async.example;

import io.runidle.async.Runidle;
import io.runidle.async.channel.pipe.B2CPipe;
import io.runidle.async.channel.BusinessChannel;
import io.runidle.async.channel.ChannelConfig;
import io.runidle.async.channel.ClientChannel;
import io.runidle.async.channel.MessageType;


public class SimpleExample {

    enum ExmapleType implements MessageType {
        Get, Save;
    }

    BusinessChannel<?> businessChannel =
            Runidle.createBusinessChannel(new ChannelConfig().setName("Business"))
                    .handler(ExmapleType.Get, (messageContext, requestContent) -> messageContext.succeed("OK"))
                    .handler(ExmapleType.Save, (messageContext, requestContent) -> messageContext.succeed("OK"))
                    .start();

    ClientChannel<?> clientChannel = Runidle.createClientChannel(new ChannelConfig().setName("server"));
    B2CPipe<?, ?> pipe = clientChannel.B2CPipe(businessChannel);

    public void get() {
        pipe.request().messageType(ExmapleType.Get).requestContent("requestData")
                .retry(3, 3000).timeout(3000).idem("a")
                .end();
    }

}
