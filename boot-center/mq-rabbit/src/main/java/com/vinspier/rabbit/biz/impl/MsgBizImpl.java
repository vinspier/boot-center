package com.vinspier.rabbit.biz.impl;

import com.vinspier.rabbit.biz.MsgBiz;
import com.vinspier.rabbit.constant.MsgConstant;
import com.vinspier.rabbit.domain.dto.MsgDTO;
import com.vinspier.rabbit.service.MsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MsgBizImpl implements MsgBiz {

    @Autowired
    private MsgService msgService;

    @Override
    public void normalMsg(MsgDTO msgDTO) {
//        MsgDTO msgDTO = MsgDTO.builder()
//                .event(MsgEventEnum.CREATE.getEvent())
//                .desc(MsgEventEnum.CREATE.getDesc())
//                .content("实际业务内容")
//                .build();
        msgService.sendMsg(msgDTO);
    }

    @Override
    public void delayMsg(MsgDTO msgDTO, int timeToLive) {
        msgService.send(msgDTO, MsgConstant.VINSPIER_DELAY_EXCHANGE,MsgConstant.DELAY_EVENT,timeToLive);
    }

}
