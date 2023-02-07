package com.vinspier.bean.life.domain;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * bean测试实体类
 *
 * @author  vinspier
 * @date    11:19 上午
 * @version 1.0
*/
public class Person implements ApplicationContextAware,BeanFactoryAware,BeanClassLoaderAware, BeanNameAware, InitializingBean , DisposableBean {

    private String name;

    private Integer age;

    private BeanFactory beanFactory;

    private String beanName;

    private ClassLoader classLoader;

    private ApplicationContext applicationContext;

    public Person() {
        System.out.println("执行【实例对像的】无参构造器  ====> 实例化");
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
        System.out.println("执行 【bean本身】 的 selfInit 初始化方法");
        this.name = "vinspier";
        this.age = 4000;
        System.out.println("        " + this.toString());
    }

    /**
     * bean 自身销毁方法
     * */
    @PreDestroy
    public void selfDestroy(){
        System.out.println("执行 【bean本身】 的 selfDestroy 销毁方法");
    }

    /**
     * BeanFactoryAware 接口方法
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("执行【BeanFactoryAware】 的 实现类 setBeanFactory 方法");
        this.beanFactory = beanFactory;
    }

    /**
     * BeanNameAware 接口方法
     */
    @Override
    public void setBeanName(String beanName) {
        System.out.println("执行【BeanNameAware】 的 实现类 setBeanName 方法");
        this.beanName = beanName;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    /**
     * InitializingBean 接口方法
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行【InitializingBean】 的 实现类 afterPropertiesSet 方法");
        // ---- 通过工厂 拿到对应的bean 对其做相应的逻辑操作
        Person person = beanFactory.getBean(Person.class);
        person.setAge(3000);
//        System.out.println("        " + JSONObject.toJSONString(person));
        System.out.println(person.toString());
    }

    /**
     * DisposableBean 接口方法
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("执行【DisposableBean】 的 实现类 destroy 方法");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", beanName='" + beanName + '\'' +
                '}';
    }
}
