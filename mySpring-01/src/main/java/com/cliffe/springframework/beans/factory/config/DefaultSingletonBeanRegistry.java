package com.cliffe.springframework.beans.factory.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Cliffe
 * @Date: 2022-11-30 5:24 下午
 */
@SuppressWarnings("all")
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry{

    private Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    public void addSingleton(String beanName, Object singletonObject){
        singletonObjects.put(beanName, singletonObject);
    }
}
