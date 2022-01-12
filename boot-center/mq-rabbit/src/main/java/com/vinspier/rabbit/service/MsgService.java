package com.vinspier.rabbit.service;

import com.vinspier.rabbit.domain.dto.MsgDTO;

public interface MsgService {

    void sendMsg(MsgDTO msgDTO);

    void send(MsgDTO msgDTO,String exchange,String routeKey);

    void send(MsgDTO msgDTO,String exchange,String routeKey,int timeToLive);
}