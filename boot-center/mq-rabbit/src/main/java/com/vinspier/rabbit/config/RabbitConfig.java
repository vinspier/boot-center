package com.vinspier.rabbit.config;

import com.vinspier.rabbit.constant.MsgConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息配置
 * 交换机 队列 路由
 */
@Configuration
public class RabbitConfig {

    @Bean
    public TopicExchange vinspier() {
        return new TopicExchange(MsgConstant.VINSPIER_EXCHANGE);
    }

    @Bean
    public Queue dev() {
        return new Queue(MsgConstant.DEV_QUEUE);
    }

    @Bean
    public Queue test() {
        return new Queue(MsgConstant.TEST_QUEUE);
    }

    @Bean
    public Queue delay() {
        return new Queue(MsgConstant.DELAY_QUEUE);
    }

    @Bean
    public Queue dead() {
        return new Queue(MsgConstant.DEAD_QUEUE);
    }

    @Bean
    public Binding bindingDev() {
        return BindingBuilder.bind(dev()).to(vinspier()).with(MsgConstant.NORMAL_EVENT);
    }

    @Bean
    public Binding bindingTest() {
        return BindingBuilder.bind(test()).to(vinspier()).with(MsgConstant.NORMAL_EVENT);
    }

    @Bean
    public Binding bindingDelay() {
        return BindingBuilder.bind(delay()).to(vinspier()).with(MsgConstant.DELAY_EVENT);
    }

    @Bean
    public Binding bindingDead() {
        return BindingBuilder.bind(dead()).to(vinspier()).with(MsgConstant.DEAD_EVENT);
    }

}
