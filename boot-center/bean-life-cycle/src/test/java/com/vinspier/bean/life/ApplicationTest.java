package com.vinspier.bean.life;

import com.alibaba.fastjson.JSONObject;
import com.vinspier.bean.life.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootTest(classes = Application.class)
public class ApplicationTest {

    @Test
    public void testBeanLifeCycle(){
        System.out.println("初始化容器开始");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        System.out.println("初始化容器结束");
        Person person = context.getBean(Person.class);
//        System.out.println("使用数据：" + JSONObject.toJSONString(person));
        System.out.println(person.toString());
        System.out.println("开始关闭容器");
        context.registerShutdownHook();

    }

}