package features.demo32_api_exception;

import features.demo32_api_exception.module1.EventUserListenerOfModule1;
import features.demo32_api_exception.module2.EventUser;

import org.junit.jupiter.api.Test;
import org.noear.dami.api.DamiApi;
import org.noear.dami.api.DamiApiImpl;
import org.noear.dami.bus.DamiBus;
import org.noear.dami.bus.DamiBusImpl;
import org.noear.dami.exception.DamiException;

public class Demo32 {
    static String topicMapping = "demo.user";
    //定义实例，避免单测干扰 //开发时用：Dami.api()
    DamiBus bus = new DamiBusImpl();
    DamiApi api = new DamiApiImpl(bus);

    @Test
    public void main() {
        Throwable testObserver = null;

        //注册监听器
        EventUserListenerOfModule1 userEventListener = new EventUserListenerOfModule1();
        api.registerListener(topicMapping, userEventListener);

        //生成发送器
        EventUser eventUser = api.createSender(topicMapping, EventUser.class);

        //发送测试
        try {
            eventUser.onCreated(1L, "noear");
        } catch (DamiException e) {
            testObserver = e.getCause();
            System.out.println("捕获到异常：");
            testObserver.printStackTrace();
        }

        //注销监听器
        api.unregisterListener(topicMapping, userEventListener);

        assert testObserver != null;
        assert testObserver.getClass() == RuntimeException.class;
    }
}
