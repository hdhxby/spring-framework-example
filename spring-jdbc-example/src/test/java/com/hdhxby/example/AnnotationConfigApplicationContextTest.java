package com.hdhxby.example;

import com.hdhxby.example.entity.Person;
import com.hdhxby.example.entity.Speech;
import com.hdhxby.example.factory.config.ApiBeanDefinitionRegistryPostProcessor;
import com.hdhxby.example.factory.config.ApiBeanFactoryPostProcessor;
import com.hdhxby.example.configuration.ThinkConfiguration;
import com.hdhxby.example.aop.ThinkAfterAdviceMethodInterceptor;
import com.hdhxby.example.aop.ThinkAfterPointcutAdvisor;
import com.hdhxby.example.aop.ThinkBeforeAdviceMethodInterceptor;
import com.hdhxby.example.aop.ThinkBeforePointcutAdvisor;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.adapter.GlobalAdvisorAdapterRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationConfigApplicationContextTest {

    /**
     * 手动添加BeanFactoryPostProcessor
     */
    @Test
    public void test(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 添加BeanFactoryPostProcessor
        applicationContext.addBeanFactoryPostProcessor(new ApiBeanFactoryPostProcessor());
        applicationContext.addBeanFactoryPostProcessor(new ApiBeanDefinitionRegistryPostProcessor());

        applicationContext.register(ThinkConfiguration.class);

        applicationContext.refresh();
        applicationContext.getBean(Speech.class).speech();
    }

    @Test
    public void testAdvice(){
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addInterface(Speech.class);
        proxyFactory.setTarget(new Person());
        proxyFactory.addAdvice(new ThinkBeforeAdviceMethodInterceptor());
        proxyFactory.addAdvice(new ThinkAfterAdviceMethodInterceptor());
        ((Speech)proxyFactory.getProxy()).speech();
    }

    @Test
    public void testAdvisor(){
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addInterface(Speech.class);
        proxyFactory.setTarget(new Person());
        proxyFactory.addAdvisor(new ThinkBeforePointcutAdvisor());
        proxyFactory.addAdvisor(new ThinkAfterPointcutAdvisor());
        ((Speech)proxyFactory.getProxy()).speech();
    }

    @Test
    public void testAdvisorAdapter(){
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addInterface(Speech.class);
        proxyFactory.setTarget(new Person());

        proxyFactory.addAdvisor(GlobalAdvisorAdapterRegistry.getInstance().wrap(new ThinkBeforeAdviceMethodInterceptor()));
        proxyFactory.addAdvisor(GlobalAdvisorAdapterRegistry.getInstance().wrap(new ThinkAfterAdviceMethodInterceptor()));
        ((Speech)proxyFactory.getProxy()).speech();
    }
}