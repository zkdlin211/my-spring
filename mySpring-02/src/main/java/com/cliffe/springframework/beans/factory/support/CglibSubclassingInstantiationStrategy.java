package com.cliffe.springframework.beans.factory.support;

import com.cliffe.springframework.BeanException;
import com.cliffe.springframework.beans.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * @Author: Cliffe
 * @Date: 2022-11-30 7:54 下午
 *
 * Cglib实例化
 */
@SuppressWarnings("all")
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> constructor, Object[] args) throws BeanException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        if(null == constructor){
            return enhancer.create();
        }
        return enhancer.create(constructor.getParameterTypes(), args);
    }
}
