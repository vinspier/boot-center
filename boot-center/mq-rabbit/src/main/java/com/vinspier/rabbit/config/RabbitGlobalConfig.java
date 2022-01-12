package com.vinspier.rabbit.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

@Slf4j
@Configuration
public class RabbitGlobalConfig {

    /**
     * 确认模式
     * */
    @Bean
    public RabbitTemplate.ConfirmCallback customizeConfirmCallback() {
        return new RabbitTemplate.ConfirmCallback() {

            private MessageConverter converter = new Jackson2JsonMessageConverter();

            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (!ack) {
                    ReturnedMessage message = correlationData.getReturned();
                    assert message != null;
                    Object msgContent = converter.fromMessage(message.getMessage());
                    log.error("message does not transfer to exchange successfully,to exchange = [{}],routeKey = [{}],replyText = [{}]，content = [{}]",message.getExchange(),message.getRoutingKey(),message.getReplyText(),msgContent);
                    // 若没有成功发送消息到 交换机
                    // 1、需要对某些业务做回退处理（释放占用的资源）
                    // 2、发出告警 通知相关人员
                    // 3、记录日志 将日志持久化到 统一的失败存储机制（DB的失败事件记录）
                }
            }

        };
    }

    /**
     * 未投递到 queue 退回模式
     * */
    @Bean
    public RabbitTemplate.ReturnsCallback customizeReturnsCallback() {
        return new RabbitTemplate.ReturnsCallback() {

            private MessageConverter converter = new Jackson2JsonMessageConverter();

            @Override
            public void returnedMessage(@Nullable ReturnedMessage returnedMessage) {
                assert returnedMessage != null;
                Object msgContent = converter.fromMessage(returnedMessage.getMessage());
                log.error("message does not transfer to queue successfully,from exchange = [{}],routeKey = [{}],replyText = [{}],content = [{}]",returnedMessage.getExchange(),returnedMessage.getRoutingKey(),returnedMessage.getReplyText(),msgContent);

                // 若没有成功发送消息到 队列
                // 1、需要对某些业务做回退处理（释放占用的资源）
                // 2、发出告警 通知相关人员
                // 3、记录投递信息日志 将日志持久化到 统一的失败存储机制 定期巡检（DB的失败事件记录）
            }
        };

    }

    /**
     * 指定序列化方式
     * 默认 Simple方式 对序列化对象严格要求（类型、包名）
     *
     * tips：确保消息的可靠性
     * 1、指定confirm回调函数(消息未成功到达交换机)
     * 2、指定returnMessage回调函数(消息为准确到达消息队列)
     *
     * */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,RabbitTemplate.ConfirmCallback confirmCallback,RabbitTemplate.ReturnsCallback returnsCallback) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnsCallback(returnsCallback);

        return rabbitTemplate;
    }

}
