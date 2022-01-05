package com.vinspier.rabbit.listener.handler;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

import java.util.concurrent.Callable;

public interface MsgHandler {

   <T,V> V handle(Message message, Channel channel, Class<T> clazz, Callable<V> callable);

}
