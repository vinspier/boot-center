package com.vinspier.template.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * 公共业务配置
 *
 * @author  xiaobiao.fan
 * @date    2022/2/15 7:37 下午
*/
@Component
@Validated
@ConfigurationProperties(prefix = "common.biz")
public class CommonBizConfig {

    /**
    * springboot 可以接入配置检查
    * */
    @NotNull(message = "测试配置code不可为空")
    private String testCode;

}
