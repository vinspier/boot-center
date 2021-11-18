package com.vinspier.search.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 分割符号 枚举
 *
 * @author  xiaobiao.fan
 * @date    2021/9/29 4:15 下午
*/
@Getter
@AllArgsConstructor
public enum SeparatorEnum {

    COMMA(",", "逗号"),
    SPACE(" ","空格"),
    GREATER(">", "大于号")
    ;

    /**
     * 符号
     * */
    private String symbol;

    /**
     * 描述
     * */
    private String desc;
}
