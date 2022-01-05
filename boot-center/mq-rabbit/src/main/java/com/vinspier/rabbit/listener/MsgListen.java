package com.vinspier.rabbit.listener;


import com.rabbitmq.client.Channel;
import com.vinspier.rabbit.constant.MsgConstant;
import com.vinspier.rabbit.domain.dto.MsgDTO;
import com.vinspier.rabbit.listener.handler.MsgAbstractHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
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
        handle(message,channel, MsgDTO.class,() -> {
            log.info("dev env 业务实际处理逻辑块");
            return Boolean.TRUE;
        });
    }

    /**
     * 常规
     * 一个交换机多个多列
     * */
    @RabbitListener(queues = {MsgConstant.TEST_QUEUE})
    public void listenTest(Message message, Channel channel) {
        log.info("--------> start handle msg at [listenTest] method");
        handle(message,channel, MsgDTO.class,() -> {
            log.info("test env  业务实际处理逻辑块");
            return Boolean.TRUE;
        });
    }

    /**
     * 延迟队列
     * */
    @RabbitListener(queues = {MsgConstant.DELAY_QUEUE})
    public void listenDelay(Message message, Channel channel) {

    }

    /**
     * 私信队列
     * */
    @RabbitListener(queues = {MsgConstant.DEAD_QUEUE})
    public void listenDead(Message message, Channel channel) {

    }

}
