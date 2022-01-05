package com.vinspier.rabbit.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.vinspier.rabbit.constant.MsgConstant;
import com.vinspier.rabbit.domain.dto.MsgDTO;
import com.vinspier.rabbit.service.MsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 通用消息发送服务层
 * */
@Slf4j
@Service
public class MsgServiceImpl implements MsgService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendMsg(MsgDTO msgDTO) {
        send(msgDTO, MsgConstant.VINSPIER_EXCHANGE,MsgConstant.NORMAL_EVENT);
    }

    @Override
    public void send(MsgDTO msgDTO,String exchange,String routeKey) {
        try {
            rabbitTemplate.convertAndSend(exchange,routeKey,msgDTO);
            log.info("[send msg succeed],msg body =[{}]", JSONObject.toJSONString(msgDTO));
        } catch (AmqpException e) {
            log.info("[send msg failed],msg body =[{}],error msg ={}", JSONObject.toJSONString(msgDTO),e.getMessage(),e);
        }
    }

}
