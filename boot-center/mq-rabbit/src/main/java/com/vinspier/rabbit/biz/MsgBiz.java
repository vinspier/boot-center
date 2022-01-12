package com.vinspier.rabbit.biz;

import com.vinspier.rabbit.domain.dto.MsgDTO;

/**
 * 消息业务操作
 * */
public interface MsgBiz {

    /**
     * topic 普通消息
     * 一个交换机 多个 队列
     * */
    void normalMsg(MsgDTO msgDTO);

    /**
     * 延时队列
     * 使用插件形式
     * */
    void delayMsg(MsgDTO msgDTO,int timeToLive);

}