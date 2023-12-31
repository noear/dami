package features.demo90_springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.noear.dami.Dami;
import org.noear.dami.bus.DamiBus;
import org.noear.dami.bus.Payload;
import org.noear.dami.bus.TopicListener;
import org.noear.dami.spring.boot.annotation.DamiTopic;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@ContextConfiguration
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@ComponentScan("features.demo90_springboot")
public class Demo90 {
    @Test
    public void main() {
        DamiBus<String, String> bus = Dami.<String, String>bus();

        System.out.println(bus.sendAndRequest("user.demo", "solon"));

        bus.sendAndSubscribe("user.demo", "dami", rst -> {
            System.out.println(rst);
        });
    }

    @DamiTopic("user.demo")
    public static class UserEventListener implements TopicListener<Payload<String, String>> {
        @Override
        public void onEvent(Payload<String, String> payload) throws Throwable {
            if (payload.isSubscribe() || payload.isRequest()) {
                payload.reply("Hi " + payload.getContent());
                payload.reply("Hi " + payload.getContent());
            }
        }
    }
}
