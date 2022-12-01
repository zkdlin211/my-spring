package com.cliffe.springframework.beans.factory.support;

import com.cliffe.springframework.BeanException;
import com.cliffe.springframework.core.io.Resource;
import com.cliffe.springframework.core.io.ResourceLoader;

/**
 * @Author: Cliffe
 * @Date: 2022-12-01 12:39 下午
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getBeanDefinitionRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeanException;

    void loadBeanDefinitions(Resource... resource) throws BeanException;

    void loadBeanDefinitions(String location) throws BeanException;
}
