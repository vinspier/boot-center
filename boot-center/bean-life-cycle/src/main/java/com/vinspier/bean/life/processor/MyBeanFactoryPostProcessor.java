package com.vinspier.bean.life.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * 工厂后置处理器
 * 
 * @author  vinspier
 * @date    2021/8/30 3:03 下午
 * @version 1.0
 * @menu    
*/
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public MyBeanFactoryPostProcessor() {
        System.out.println("执行【BeanFactoryPostProcessor】 的 实现类 MyBeanFactoryPostProcessor 类构造方法 ====> 实例化");
    }

    /**
     * 容器级别
     * 对于整个工厂创建出来的BeanDefinition进行操作
     *
     * 作用于 BeanDefinition
     * 时期：在 实例化 之前调用
     * */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("执行【BeanFactoryPostProcessor】 的 实现类 postProcessBeanFactory 方法 ========> 操作BeanDefinition");
        BeanDefinition definition = configurableListableBeanFactory.getBeanDefinition("person");
        // 后置处理器 修改属性
        definition.getPropertyValues().add("age",1000);
    }
}
