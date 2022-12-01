package com.cliffe.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.cliffe.springframework.BeanException;
import com.cliffe.springframework.beans.PropertyValue;
import com.cliffe.springframework.beans.PropertyValues;
import com.cliffe.springframework.beans.factory.config.BeanDefinition;
import com.cliffe.springframework.beans.factory.config.BeanReference;
import com.sun.scenario.effect.Effect;
import lombok.Data;

import java.lang.reflect.Constructor;

/**
 * @Author: Cliffe
 * @Date: 2022-11-30 6:17 下午
 * <p>
 * 通过继承实例化策略接口InstantiationStrategy，
 * <p>
 * 让外部调用时可以传递构造函数的入参信息进行实例化
 */
@Data
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeanException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
            //初始化后填充bean属性
            addPropertyValues(beanName, bean, beanDefinition);
        } catch (BeanException e) {
            throw new BeanException("Instantiation of bean failed", e);
        }
        addSingleton(beanName, bean);
        return bean;
    }

    /**
     * 填充属性
     */
    protected void addPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) throws BeanException {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            if(propertyValues == null){
                return;
            }
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                if (value instanceof BeanReference) {//该属性是引用的另一个bean对象
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (BeanException e) {
            throw new BeanException("Error setting property values：" + beanName);
        }
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) throws BeanException {
        Constructor<?> constructor = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            if (null != args && args.length == declaredConstructor.getParameterCount()) {
                constructor = declaredConstructor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructor, args);
    }

}
