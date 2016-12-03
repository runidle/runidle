package io.runidle.async;

import io.runidle.async.channel.*;

public class Runidle {
    public static BusinessChannel<?> createBusinessChannel(ChannelConfig channelConfig) {
        return new BusinessChannelImpl<>(channelConfig);
    }

    public static ServerChannel<?> createServerChannel(ChannelConfig channelConfig) {
        return new ServerChannelImpl<>(channelConfig);
    }

    public static ClientChannel<?> createClientChannel(ChannelConfig channelConfig) {
        return new ClientChannelImpl<>(channelConfig);
    }
}
