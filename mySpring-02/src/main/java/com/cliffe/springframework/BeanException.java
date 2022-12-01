package com.cliffe.springframework;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.util.logging.Logger;

/**
 * @Author: Cliffe
 * @Date: 2022-11-30 6:12 下午
 */
@Slf4j
public class BeanException extends Exception {

    public BeanException(String s) {
        log.error(s);
    }

    public BeanException() {
    }

    public BeanException(String s, Exception e) {
        log.error(s);
        e.printStackTrace();
    }
}
