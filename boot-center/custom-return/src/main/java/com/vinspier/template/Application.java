package com.vinspier.template;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
@MapperScan(basePackages = "com.vinspier.template.mapper")
public class Application {

    public static void main(String[] args) {
        log.info("===> 程序开始执行启动 ");
        ConfigurableApplicationContext context = SpringApplication.run(Application.class,args);
        log.info("===> 程序正常启动 环境配置:{}", JSONObject.toJSONString(context.getEnvironment()));
        // 程序退出 回调入口
//        SpringApplication.exit(context);

        // ------------ builder方式创建 ------------------
        SpringApplication application = new SpringApplicationBuilder()
                // 不加载命令行参数 --server.port=8080
                .addCommandLineProperties(true)
                .build();
        application.run(args);
    }

}
