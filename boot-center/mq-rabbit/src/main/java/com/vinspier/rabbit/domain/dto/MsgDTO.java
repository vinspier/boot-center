package com.vinspier.rabbit.domain.dto;

import lombok.*;

import java.io.Serializable;

/**
 * 消息体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class MsgDTO implements Serializable {

    /**
     * 事件类型
     * */
    private Integer event;

    /**
     * 描述
     * */
    private String desc;

    /**
     * 消息内容体json格式
     * */
    private String content;

    /**
     * 附加信息
     * */
    private String external;

}
