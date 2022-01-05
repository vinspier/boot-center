package com.vinspier.rabbit.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 消息时间枚举
 * */
@Getter
@AllArgsConstructor
public enum MsgEventEnum {

    CREATE(1,"新增"),
    MODIFY(2,"修改"),
    DELETE(3,"删除"),
    ALLOCATE(4,"分配");

    /**
     * 时间
     * */
    private final Integer event;

    /**
     * 描述
     * */
    private final String desc;

}
