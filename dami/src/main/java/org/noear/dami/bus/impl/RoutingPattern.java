package org.noear.dami.bus.impl;

import org.noear.dami.bus.Payload;
import org.noear.dami.bus.TopicListener;

import java.util.regex.Pattern;

/**
 * 监听路由记录
 */
public class RoutingPattern<C, R> extends RoutingBase<C, R> {

    private final Pattern pattern;

    /**
     * @param expr     表达式（* 表示一段，** 表示不限段）
     * @param index    顺序位
     * @param listener 监听器
     */
    public RoutingPattern(String expr, int index, TopicListener<Payload<C, R>> listener) {
        super(expr, index, listener);

        if (expr.contains("*")) {
            expr = expr.replace(".", "\\."); //支持 . 或 / 做为隔断

            //替换中间的**值
            expr = expr.replace("**", ".[]");

            //替换*值
            expr = expr.replace("*", "[^/\\.]*");

            //替换**值
            expr = expr.replace(".[]", ".*");

            //加头尾
            expr = "^" + expr + "$";

            this.pattern = Pattern.compile(expr);
        } else {
            this.pattern = null;
        }
    }

    /**
     * 匹配
     *
     * @param topic 主题
     */
    public boolean matches(String topic) {
        if (super.matches(topic)) {
            return true;
        }

        if (pattern != null) {
            return pattern.matcher(topic).find();
        }

        return false;
    }
}