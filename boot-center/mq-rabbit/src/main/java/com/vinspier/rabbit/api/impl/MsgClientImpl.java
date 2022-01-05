package com.vinspier.rabbit.api.impl;

import com.vinspier.rabbit.api.MsgClient;
import com.vinspier.rabbit.biz.MsgBiz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MsgClientImpl implements MsgClient {

    @Autowired
    private MsgBiz msgBiz;

}
