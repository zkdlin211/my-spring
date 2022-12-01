package com.cliffe.springframework.test;

import com.cliffe.springframework.BeanException;
import com.cliffe.springframework.beans.PropertyValue;
import com.cliffe.springframework.beans.PropertyValues;
import com.cliffe.springframework.beans.factory.config.BeanDefinition;
import com.cliffe.springframework.beans.factory.config.BeanReference;
import com.cliffe.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.cliffe.springframework.test.entity.User;
import com.cliffe.springframework.test.entity.UserDao;
import com.cliffe.springframework.test.entity.UserService;
import org.junit.Test;

/**
 * @Author: Cliffe
 * @Date: 2022-12-01 9:33 上午
 */
public class ApiTest {

    @Test
    public void test() throws BeanException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(User.class);
        beanFactory.registerBeanDefinition("user", beanDefinition);
        User bean = (User) beanFactory.getBean("user", "user1", 18);
        System.out.println(bean.toString());
    }

    @Test
    public void testBeanInjection() throws BeanException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //注册userDao
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));
        PropertyValues propertyValues = new PropertyValues();
        //依赖注入
        propertyValues.addPropertyValue(new PropertyValue("accessKey", "12345"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        //注册userService
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService");
        System.out.println(userService.getUserInfo("k1"));
    }
}
