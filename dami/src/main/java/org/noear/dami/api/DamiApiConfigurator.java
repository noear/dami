package org.noear.dami.api;

/**
 * 大米接口配置器
 *
 * @author noear
 * @since 1.0
 */
public interface DamiApiConfigurator extends  DamiApi{
    /**
     * 设置编码器
     *
     * @param coder 编码器
     */
    DamiApiConfigurator coder(Coder coder);
}
