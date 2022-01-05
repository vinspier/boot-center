package com.vinspier.rabbit.biz.impl;

import com.vinspier.rabbit.biz.MsgBiz;
import com.vinspier.rabbit.domain.dto.MsgDTO;
import com.vinspier.rabbit.domain.enums.MsgEventEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class MsgBizImplTest {

    @Autowired
    private MsgBiz msgBiz;

    @Test
    public void normalMsg() {
        MsgDTO msgDTO = MsgDTO.builder()
        .event(MsgEventEnum.CREATE.getEvent())
        .desc(MsgEventEnum.CREATE.getDesc())
        .content("实际业务内容")
        .external("")
        .build();

        msgBiz.normalMsg(msgDTO);
    }

}