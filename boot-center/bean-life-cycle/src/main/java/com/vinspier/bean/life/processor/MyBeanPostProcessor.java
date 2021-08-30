package com.vinspier.bean.life.processor;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 后置处理器
 *
 * @author  vinspier
 * @date    2021/8/30 2:53 下午
 * @version 1.0
*/
public class MyBeanPostProcessor implements BeanPostProcessor {

    public MyBeanPostProcessor() {
        System.out.println("执行【BeanPostProcessor】的 MyBeanPostProcessor 类构造方法");
    }

    /**
     * bean 要处理的bean对象
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("执行【BeanPostProcessor】的 postProcessBeforeInitialization 方法");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("执行【BeanPostProcessor】的 postProcessAfterInitialization 方法 对属性进行修改");
        return bean;
    }
}
