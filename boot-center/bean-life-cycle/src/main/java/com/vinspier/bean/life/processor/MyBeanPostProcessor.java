package com.vinspier.bean.life.processor;


import com.alibaba.fastjson.JSONObject;
import com.vinspier.bean.life.domain.Person;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * bean处理器
 * 作用于 bean实例化、初始化的前后执行
 *
 * @author  vinspier
 * @date    2021/8/30 2:53 下午
 * @version 1.0
*/
public class MyBeanPostProcessor implements BeanPostProcessor {

    public MyBeanPostProcessor() {
        System.out.println("执行【BeanPostProcessor】 的 实现类 MyBeanPostProcessor 类构造方法  ====> 实例化");
    }

    /**
     * bean初始化的前置操作
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("执行【BeanPostProcessor】 的 实现类 postProcessBeforeInitialization 方法 ====> bean初始化的前置操作");
        ((Person) bean).setAge(2000);
//        System.out.println("        " + beanName + "：" + JSONObject.toJSONString(bean));
//        System.out.printf("name:%s age:%s%n",((Person) bean).getName(),((Person) bean).getAge());
        System.out.println(((Person) bean));
        return bean;
    }

    /**
     * bean初始化的后置操作
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("执行【BeanPostProcessor】 的 实现类 postProcessAfterInitialization 方法 ====> bean初始化的后置操作");
        ((Person) bean).setAge(5000);
//        System.out.println("        " + beanName + "：" + JSONObject.toJSONString(bean));
//        System.out.printf("name:%s age:%s%n",((Person) bean).getName(),((Person) bean).getAge());
        System.out.println(((Person) bean));
        return bean;
    }
}
