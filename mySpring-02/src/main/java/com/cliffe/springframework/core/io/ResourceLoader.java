package com.cliffe.springframework.core.io;

/**
 * @Author: Cliffe
 * @Date: 2022-12-01 12:32 下午
 */
public interface ResourceLoader {
    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);
}
