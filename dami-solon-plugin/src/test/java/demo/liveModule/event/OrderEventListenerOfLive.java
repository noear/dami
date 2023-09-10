package demo.liveModule.event;

import org.noear.dami.solon.annotation.DamiTopic;

@DamiTopic("demo.order")
public class OrderEventListenerOfLive {
    public void onCreated(long orderId) {
        System.err.println("LIve:Order:onCreated: orderId=" + orderId);
    }
}
