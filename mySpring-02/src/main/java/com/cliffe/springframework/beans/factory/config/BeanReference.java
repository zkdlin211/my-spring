package com.cliffe.springframework.beans.factory.config;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: Cliffe
 * @Date: 2022-12-01 10:27 上午
 *
 * Bean依赖的另一个bean(@Autowired)
 */
@AllArgsConstructor
@Data
public class BeanReference {

    private final String beanName;

}
