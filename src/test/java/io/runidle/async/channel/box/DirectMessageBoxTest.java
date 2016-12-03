package io.runidle.async.channel.box;

import org.testng.annotations.Test;

public class DirectMessageBoxTest extends MessageBoxTest {
    @Test
    public void testMessageHandler() {
        MessageBox messageBox = new DirectMessageBox("test");
        doTest(messageBox);
    }
}
