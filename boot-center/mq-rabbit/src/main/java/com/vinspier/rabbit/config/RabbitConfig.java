package com.vinspier.rabbit.config;

import com.vinspier.rabbit.constant.MsgConstant;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

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

    /**
     * 配置延迟交换机
     * */
    @Bean
    public CustomExchange vinspierDelay() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(MsgConstant.VINSPIER_DELAY_EXCHANGE,"x-delayed-message",true,false,args);
    }

    /**
     * 配置死信交换机
     * */
    @Bean
    public TopicExchange vinspierDead() {
        return new TopicExchange(MsgConstant.VINSPIER_DEAD_EXCHANGE);
    }

    @Bean
    public Queue dev() {
        return QueueBuilder.durable(MsgConstant.DEV_QUEUE).build();
    }

    @Bean
    public Queue test() {
        // 绑定死信交换机
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange",MsgConstant.VINSPIER_DEAD_EXCHANGE);
        arguments.put("x-dead-letter-routing-key",MsgConstant.DEAD_EVENT);
        return QueueBuilder.durable(MsgConstant.TEST_QUEUE).withArguments(arguments).build();
    }

    @Bean
    public Queue delay() {
        return QueueBuilder.durable(MsgConstant.DELAY_QUEUE).build();
    }

    @Bean
    public Queue dead() {
        return QueueBuilder.durable(MsgConstant.DEAD_QUEUE).build();
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
        return BindingBuilder.bind(delay()).to(vinspierDelay()).with(MsgConstant.DELAY_EVENT).noargs();
    }

    @Bean
    public Binding bindingDead() {
        return BindingBuilder.bind(dead()).to(vinspierDead()).with(MsgConstant.DEAD_EVENT);
    }

}
