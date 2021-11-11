package com.vinspier.search;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@Slf4j
@SpringBootApplication
@MapperScan(basePackages = "com.vinspier.search.mapper")
public class SearchApplication {

    public static void main(String[] args) {
        System.setProperty("app.name", "item-center");
        ApplicationContext ctx = SpringApplication.run(SearchApplication.class, args);
        log.info("spring boot 启动环境和配置信息如下：" + ctx.getEnvironment());
    }

}
