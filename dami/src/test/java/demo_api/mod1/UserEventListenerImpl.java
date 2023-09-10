package demo_api.mod1;

/**
 * 通过约定保持与 Sender 相同的接口定义（或者实现 UserEventSender 接口）
 * */
public class UserEventListenerImpl {
    public long created(long userId, String name) {
        System.err.println("created: userId=" + userId + ", name=" + name);
        return userId;
    }
    public void updated(long userId, String name) {
        System.err.println("updated: userId=" + userId + ", name=" + name);
    }
}