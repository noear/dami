package org.noear.dami.api.impl;

import org.noear.dami.api.DamiApi;
import org.noear.dami.bus.TopicListener;
import org.noear.dami.bus.Payload;

import java.lang.reflect.Method;

/**
 * 方法主题监听器
 *
 * @author noear
 * @since 1.0
 */
public class MethodTopicListener implements TopicListener<Payload<Object,Object>> {
    private DamiApi damiApi;
    private Object target;
    private Method method;

    public MethodTopicListener(DamiApi damiApi, Object target, Method method) {
        this.damiApi = damiApi;
        this.target = target;
        this.method = method;
    }

    @Override
    public void onEvent(Payload payload) throws Throwable {
        //解码
        Object[] args = damiApi.coder().decode(method, payload);

        //执行
        Object rst = method.invoke(target, args);

        if (payload.isRequest()) {
            //答复
            payload.reply(rst);
        }
    }

    @Override
    public String toString() {
        return target.getClass().getName() + "::" + method.getName();
    }
}