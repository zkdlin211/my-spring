package com.cliffe.springframework.beans.factory.config;

import com.cliffe.springframework.BeanException;

/**
 * @Author: Cliffe
 * @Date: 2022-11-30 5:23 下午
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);
}
