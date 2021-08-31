package com.vinspier.bean.life.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

/**
 * 后置处理接口
 * InstantiationAwareBeanPostProcessorAdapter适配类 本质是 BeanPostProcessor 的接口
 *
 * @author  vinspier
 * @date    2021/8/30 2:59 下午
 * @version 1.0
 * @menu
*/
public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {

    public MyInstantiationAwareBeanPostProcessor() {
        System.out.println("执行【InstantiationAwareBeanPostProcessor】 的 实现类 MyInstantiationAwareBeanPostProcessor 类构造方法  ====> 实例化");
    }

    /**
     * 实例化bean之前调用
     * 直接返回一个对象（例如代理对象）
     *
     * 作用：代替内置的实例化创建对象流程
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("执行【InstantiationAwareBeanPostProcessor】的 postProcessBeforeInstantiation 方法  ========> 执行实例化前置操作");
        return super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    /**
     * 实例化bean之后调用
     *
     * 若返回false 则会对指定的bean不进行自动依赖注入
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println("执行【InstantiationAwareBeanPostProcessor】的 postProcessAfterInstantiation 方法  ========> 执行实例化后置操作");
        return super.postProcessAfterInstantiation(bean, beanName);
    }

    /**
     * 设置某个属性的时候调用
     */
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        System.out.println("执行【InstantiationAwareBeanPostProcessor】 的 实现类 postProcessProperties 方法 设置属性");
        return super.postProcessProperties(pvs, bean, beanName);
    }
}
