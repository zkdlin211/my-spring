package com.cliffe.springframework.utils;

/**
 * @Author: Cliffe
 * @Date: 2022-12-01 11:26 上午
 */
public class ClassUtils {

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader classLoader = null;
        try {
            classLoader  = Thread.currentThread().getContextClassLoader();
        } catch (Exception e) {

        }
        if(classLoader == null){
            classLoader = ClassUtils.class.getClassLoader();
        }
        return classLoader;
    }

}
