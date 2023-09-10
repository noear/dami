package demo.orderModule.event;

import demo.baseModule.model.User;
import org.noear.dami.solon.annotation.DamiTopic;

@DamiTopic("demo.user")
public interface UserDemandSender {
    User getUser(long userId);
}
