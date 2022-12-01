package com.cliffe.springframework.beans.factory.config;

import com.cliffe.springframework.beans.PropertyValue;
import com.cliffe.springframework.beans.PropertyValues;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Cliffe
 * @Date: 2022-11-30 5:11 下午
 */
@Data
@AllArgsConstructor
public class BeanDefinition {

    private Class<?> beanClass;

    private PropertyValues propertyValues;

    public BeanDefinition(Class<?> beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }
}
