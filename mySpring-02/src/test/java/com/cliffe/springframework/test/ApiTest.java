package com.cliffe.springframework.test;

import cn.hutool.core.io.IoUtil;
import com.cliffe.springframework.BeanException;
import com.cliffe.springframework.beans.PropertyValue;
import com.cliffe.springframework.beans.PropertyValues;
import com.cliffe.springframework.beans.factory.config.BeanDefinition;
import com.cliffe.springframework.beans.factory.config.BeanReference;
import com.cliffe.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.cliffe.springframework.core.io.DefaultResourceLoader;
import com.cliffe.springframework.core.io.Resource;
import com.cliffe.springframework.test.entity.User;
import com.cliffe.springframework.test.entity.UserDao;
import com.cliffe.springframework.test.entity.UserService;
import org.junit.Before;
import org.junit.Test;
import sun.nio.ch.IOUtil;

import java.io.IOException;

/**
 * @Author: Cliffe
 * @Date: 2022-12-01 9:33 上午
 */
public class ApiTest {

    private DefaultResourceLoader resourceLoader;

    @Before
    public void init(){
        resourceLoader = new DefaultResourceLoader();
    }


    @Test
    public void testClassPath() throws IOException {
        Resource resource = resourceLoader.getResource("src/test/resources/myspring.properties");
        String content = IoUtil.readUtf8(resource.getInputStream());
        System.out.println(content);
    }

    @Test
    public void testUrl() throws IOException {
        Resource resource = resourceLoader.getResource("https://github.com/onlycliffe/springcloud-config/blob/main/config-dev.yml");
        String content = IoUtil.readUtf8(resource.getInputStream());
        System.out.println(content);
    }

}
