package com.cliffe.springframework.test;

import com.cliffe.springframework.BeanException;
import com.cliffe.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.cliffe.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.cliffe.springframework.test.entity.UserDao;
import com.cliffe.springframework.test.entity.UserService;
import org.junit.Test;

/**
 * @Author: Cliffe
 * @Date: 2022-12-01 3:21 下午
 */
public class TestXmlRegisterBean {

    @Test
    public void testXml() throws BeanException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("src/test/resources/spring.xml");
        UserDao userDao = beanFactory.getBean("userDao", UserDao.class);
        UserService userService = beanFactory.getBean("userService", UserService.class);
        System.out.println(userService.getUserInfo("k1"));
        System.out.println(userDao);

    }
}
