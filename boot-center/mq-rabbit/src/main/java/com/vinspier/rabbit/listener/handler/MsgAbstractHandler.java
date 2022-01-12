package com.vinspier.rabbit.listener.handler;


import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

import java.io.IOException;
import java.util.concurrent.Callable;

@Slf4j
public abstract class MsgAbstractHandler implements MsgHandler {

    private static final int defaultRetry = 3;

    /**
     * 反序列化 转换器
     * */
    private MessageConverter converter = new Jackson2JsonMessageConverter();

    /**
     * 转换 业务实体
     * */
    protected <T> T convertMsgDTO(Message message) {
        T resultDTO;
        try {
            resultDTO = (T) converter.fromMessage(message);
        } catch (MessageConversionException e) {
            log.error("convert from message to dto failed error msg content = [{}] ",e.getMessage(),e);
            throw e;
        }

        return resultDTO;
    }

    /**
     * 消息处理流程编排
     * */
    protected  <T, V> V handle(Message message, Channel channel, Callable<V> callable,int retryTimes) {
        T dto = convertMsgDTO(message);
        preHandle(dto);
        V result = execute(callable,retryTimes);
        afterHandle(message,channel);
        // ack这个动作 应该在处理消息返回结果后 业务自行判断 ack、nack、reject
//        ack(message,channel);

        return result;
    }

    /**
     * 消息处理流程编排
     * */
    @Override
    public <T, V> V handle(Message message, Channel channel, Callable<V> callable) {
        return handle(message,channel,callable,defaultRetry);
    }

    /**
     * 前置处理
     * */
    protected <T> void preHandle(T dto){
        log.info("--------> [pre handle msg] received msg content =[{}]", JSONObject.toJSONString(dto));
    }

    /**
     * 处理执行
     * */
    protected <V> V execute(Callable<V> callable,int retry){
        V v = null;
        int executeTimes = 1;
        while (executeTimes <= retry){
            try {
                v = callable.call();
                log.info("--------> [execute msg succeed] at executeTimes = [{}]",executeTimes);
                break;
            } catch (Exception e) {
                log.error("--------> [execute msg failed] at executeTimes = [{}],error msg content = [{}]",executeTimes,e);
            }
            executeTimes++;
        }
        return v;
    }

    /**
     * 后置处理
     * */
    protected <V> void afterHandle(Message message,Channel channel){
        log.info("--------> [after handle msg]");
    }

    /**
     * ack
     * */
    protected void ack(Message message,Channel channel){
        try {
            // ack消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
        } catch (IOException e) {
            log.error("-----> [channel operate ack failed] after execute the afterHandle method,exception msg =[{}]",e.getMessage(), e);
        }
    }

    /**
     * reject
     * */
    protected void reject(Message message,Channel channel,boolean requeue){
        try {
            // ack消息
            channel.basicReject(message.getMessageProperties().getDeliveryTag(),requeue);
        } catch (IOException e) {
            log.error("-----> [channel operate reject failed] after execute the afterHandle method,exception msg =[{}]",e.getMessage(), e);
        }
    }

    /**
     * nack
     * */
    protected void nack(Message message,Channel channel){
        try {
            // ack消息
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
        } catch (IOException e) {
            log.error("-----> [channel operate nack failed] after execute the afterHandle method,exception msg =[{}]",e.getMessage(), e);
        }
    }

}
