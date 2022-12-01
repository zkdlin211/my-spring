package com.cliffe.springframework.beans.factory;

import com.cliffe.springframework.BeanException;
import com.cliffe.springframework.beans.factory.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Cliffe
 * @Date: 2022-11-30 5:12 下午
 */
public interface BeanFactory {

     Object getBean(String name) throws BeanException;

     /**
      * 含有入参信息的getBean方法
      */
     Object getBean(String name, Object... args) throws BeanException;

}
