package com.cliffe.springframework.beans.factory.support;

import com.cliffe.springframework.BeanException;
import com.cliffe.springframework.beans.factory.BeanFactory;
import com.cliffe.springframework.beans.factory.config.BeanDefinition;
import com.cliffe.springframework.beans.factory.config.DefaultSingletonBeanRegistry;

/**
 * @Author: Cliffe
 * @Date: 2022-11-30 6:02 下午
 * 实现BeanFactory：创建bean/getBean能力
 * 继承DefaultSingletonBeanRegistry：注册（单例）的能力
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String beanName, Object... args) throws BeanException {
        return doGetBean(beanName, args);
    }

    protected <T> T doGetBean(final String beanName, final Object[] args) throws BeanException {
        Object bean = getSingleton(beanName);
        if(null != bean){
            return (T) bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return (T) createBean(beanName, beanDefinition, args);
    }

    @Override
    public Object getBean(String beanName) throws BeanException {
        return doGetBean(beanName, null);
    }

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeanException;

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeanException;
}
