package com.cliffe.springframework.beans.factory.support;

import com.cliffe.springframework.beans.factory.config.BeanDefinition;

/**
 * @Author: Cliffe
 * @Date: 2022-11-30 7:16 下午
 */
public interface BeanDefinitionRegistry {
    /**
     * 向注册表中注册 BeanDefinition
     *
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
