package com.cliffe.springframework.beans.factory.support;

import com.cliffe.springframework.BeanException;
import com.cliffe.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @Author: Cliffe
 * @Date: 2022-11-30 7:45 下午
 *
 * Bean实例化策略接口
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName,
                       Constructor<?> constructor, Object[] args) throws BeanException;
}
