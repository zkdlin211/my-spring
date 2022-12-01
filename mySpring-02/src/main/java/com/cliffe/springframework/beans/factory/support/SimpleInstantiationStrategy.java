package com.cliffe.springframework.beans.factory.support;

import com.cliffe.springframework.BeanException;
import com.cliffe.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author: Cliffe
 * @Date: 2022-11-30 7:47 下午
 * <p>
 * JDK实例化
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> constructor, Object[] args) throws BeanException {
        Class<?> clazz = beanDefinition.getBeanClass();

        try {
            if (null != constructor) {
                return clazz.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(args);
            }else{//为空则是无参构造
                return clazz.getDeclaredConstructor().newInstance();
            }
        } catch (InstantiationException | IllegalAccessException
                | InvocationTargetException | NoSuchMethodException e) {
            throw new BeanException("Fail tp instantiate [" + clazz.getName() + "]", e);
        }
    }
}
