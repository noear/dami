package features.demo23_unlistenall;

import org.junit.jupiter.api.Test;
import org.noear.dami.bus.DamiBus;
import org.noear.dami.bus.DamiBusImpl;
import org.noear.dami.bus.Payload;
import org.noear.dami.bus.TopicListener;

public class Demo23 {
    static String topic = "demo.hello";
    //定义实例，避免单测干扰 //开发时用：Dami.<String,String>bus()
    DamiBus<String, Long> bus = new DamiBusImpl<>();

    @Test
    public void main() throws InterruptedException {
        TopicListener<Payload<String, Long>> aListener = payload -> {
            System.out.println("i'm a:" + payload);
        };
        TopicListener<Payload<String, Long>> bListener = payload -> {
            System.out.println("i'm b:" + payload);
        };

        TopicListener<Payload<String, Long>> cListener = payload -> {
            System.out.println("i'm b:" + payload);
        };
        //监听事件
        bus.listen(topic,aListener);
        bus.listen(topic,bListener);
        bus.listen(topic,cListener);
        System.out.println("------------------ 从未移除监听器之前 ------------------");
        //发送事件
        bus.send(topic, "aaaaaa");
        System.out.println("------------------ 移除 a 监听器之后 ------------------");
        bus.unlisten(topic,aListener);
        bus.send(topic, "bbbbbb");
        System.out.println("------------------ 移除所有监听器之后 ------------------");
        bus.unlisten(topic);
        bus.send(topic, "cccccc");
    }
}
