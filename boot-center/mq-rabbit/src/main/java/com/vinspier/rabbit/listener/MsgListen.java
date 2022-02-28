package com.vinspier.rabbit.listener;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.vinspier.rabbit.constant.MsgConstant;
import com.vinspier.rabbit.listener.handler.MsgAbstractHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 消息监听
 * */
@Slf4j
@Component
public class MsgListen extends MsgAbstractHandler {

    /**
     * 常规
     * 一个交换机多个多列
     * */
    @RabbitListener(queues = {MsgConstant.DEV_QUEUE})
    public void listenDev(Message message, Channel channel) {
        log.info("--------> start handle msg at [listenDev] method");
        Boolean result = handle(message,channel,() -> {
            log.info("dev env 业务实际处理逻辑块");
            return Boolean.TRUE;
        });
        if (Objects.nonNull(result) && result) {
            ack(message, channel);
        }else {
            reject(message, channel,false);
        }
    }

    /**
     * 常规
     * 一个交换机多个多列
     * */
    @RabbitListener(queues = {MsgConstant.TEST_QUEUE})
    public void listenTest(Message message, Channel channel){
        log.info("--------> start handle msg at [listenTest] method");
        Boolean result = handle(message,channel, () -> {
            log.info("test env  业务实际处理逻辑块");
            return Boolean.TRUE;
        });
        if (Objects.nonNull(result) && result) {
            ack(message, channel);
        }else {
            // 重要业务场景 不能丢失消息
            nack(message, channel);
        }
    }

    /**
     * 延迟队列
     * */
    @RabbitListener(queues = {MsgConstant.DELAY_QUEUE})
    public void listenDelay(Message message, Channel channel) {
        log.info("--------> start handle msg at [listenDelay] method");
        Boolean result = handle(message,channel, () -> {
            log.info("delay env  业务实际处理逻辑块");
            return Boolean.TRUE;
        });
        if (Objects.nonNull(result) && result) {
            ack(message, channel);
        }else {
            // 重要业务场景 不能丢失消息
            reject(message, channel,false);
        }
    }

    /**
     * 私信队列
     * */
    @RabbitListener(queues = {MsgConstant.DEAD_QUEUE})
    public void listenDead(Message message, Channel channel) {
        log.info("--------> start handle msg at [listenDead] method");
        log.info("-----> msg headers content = [{}]", JSONObject.toJSONString(message.getMessageProperties().getHeaders()));
        Boolean result = handle(message,channel,() -> {
            log.info("dead env  业务实际处理逻辑块");
            return Boolean.TRUE;
        });
        if (Objects.nonNull(result) && result) {
            ack(message, channel);
        }else {
            // 策略二：尝试次数最大后(tips:基本就是数据问题) 记录到db中 做个控制台模块 可查看这块的事件
            // 策略一：死信队列情况下 允许重回队列
            reject(message,channel,true);
        }
    }

    /**
     * 注解式配置 队列 + 消息
     * */
    @RabbitListener(bindings = {
            @QueueBinding(
                    exchange = @Exchange(value = MsgConstant.VINSPIER_EXCHANGE, type = "topic"),
                    value = @Queue(MsgConstant.ANNOTATION_QUEUE),
                    key = MsgConstant.ANNOTATION_EVENT
            )
    }, concurrency = "1-1")
    public void listen(Message message, Channel channel) {
        handle(message,channel,() -> {
            log.info("client annotation config consumer  业务实际处理逻辑块");
            return Boolean.TRUE;
        });
        ack(message,channel);
    }

}
