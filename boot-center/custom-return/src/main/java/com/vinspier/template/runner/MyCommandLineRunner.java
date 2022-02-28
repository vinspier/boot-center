package com.vinspier.template.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 在应用程序启动之后执行任务
 * 还一个相同功能的接口ApplicationRunner
 *
 * order 多个情况下 指定顺序
 * */
@Slf4j
@Order(1)
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("===> 程序启动之后 执行runner");
    }
}
