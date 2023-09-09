package org.noear.dami.api;

/**
 * 大米接口（提供 Local Procedure Call 服务）
 *
 * @author noear
 * @since 1.0
 */
public interface DamiApi {
    /**
     * 获取编码器
     */
    Coder getCoder();

    /**
     * 设置编码器
     */
    void setCoder(Coder coder);

    /**
     * 创建发送器代理
     *
     * @param topicMapping 主题映射
     * @param senderClz    发送器接口类
     */
    <T> T createSender(String topicMapping, Class<T> senderClz);

    /**
     * 注册监听者实例
     *
     * @param topicMapping 主题映射
     * @param listenerObj  监听器实现类
     */
    void registerListener(String topicMapping, Object listenerObj);
}
