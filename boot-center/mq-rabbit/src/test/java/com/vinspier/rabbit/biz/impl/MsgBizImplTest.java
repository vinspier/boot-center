package com.vinspier.rabbit.biz.impl;

import com.vinspier.rabbit.biz.MsgBiz;
import com.vinspier.rabbit.constant.MsgConstant;
import com.vinspier.rabbit.domain.dto.MsgDTO;
import com.vinspier.rabbit.domain.enums.MsgEventEnum;
import com.vinspier.rabbit.service.MsgService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@Slf4j
@SpringBootTest
public class MsgBizImplTest {

    @Autowired
    private MsgBiz msgBiz;

    @Autowired
    private MsgService msgService;

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

    @Test
    public void delayMsg() {
        MsgDTO msgDTO = MsgDTO.builder()
                .event(MsgEventEnum.CREATE.getEvent())
                .desc(MsgEventEnum.CREATE.getDesc())
                .content("延时消息内容")
                .external("")
                .build();

        msgBiz.delayMsg(msgDTO,30);
    }

    @Test
    public void annotationMsg() throws InterruptedException {
        int index = 0;
        while (true) {
            MsgDTO msgDTO = MsgDTO.builder()
                    .event(MsgEventEnum.CREATE.getEvent())
                    .desc(MsgEventEnum.CREATE.getDesc())
                    .external("-消息内容-" + index++)
                    .build();
            msgService.send(msgDTO, MsgConstant.VINSPIER_EXCHANGE,MsgConstant.ANNOTATION_EVENT);
        }
    }

    @Test
    public void test() {
        ArrayList<Integer> arrayList = new ArrayList<>();

        Class<? extends ArrayList> clazz = arrayList.getClass();
        clazz.getGenericSuperclass();

        clazz.getGenericInterfaces();
    }

}