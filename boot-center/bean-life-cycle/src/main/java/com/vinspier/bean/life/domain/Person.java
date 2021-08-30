package com.vinspier.bean.life.domain;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * bean测试实体类
 *
 * @author  vinspier
 * @date    11:19 上午
 * @version 1.0
*/
public class Person implements BeanFactoryAware, BeanNameAware, InitializingBean , DisposableBean {

    private String name;

    private Integer age;

    private BeanFactory beanFactory;

    private String beanName;

    public Person() {
        System.out.println("执行【实例对像的】无参构造器");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public String getBeanName() {
        return beanName;
    }

    /**
     * bean 自身初始化方法
     * */
    @PostConstruct
    public void selfInit(){
        this.name = "vinspier";
        this.age = 26;
    }

    /**
     * bean 自身销毁方法
     * */
    @PreDestroy
    public void selfDestroy(){
        System.out.println("执行 bean本省 的 selfDestroy 销毁方法");
    }

    /**
     * BeanFactoryAware 接口方法
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("执行【BeanFactoryAware】的 setBeanFactory 方法");
        this.beanFactory = beanFactory;
    }

    /**
     * BeanNameAware 接口方法
     */
    @Override
    public void setBeanName(String beanName) {
        System.out.println("执行【BeanNameAware】的 setBeanName 方法");
        this.beanName = beanName;
    }

    /**
     * InitializingBean 接口方法
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行【InitializingBean】的 afterPropertiesSet 方法");
    }

    /**
     * DisposableBean 接口方法
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("执行【DisposableBean】的 destroy 方法");
    }
}
