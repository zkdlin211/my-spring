package com.cliffe.springframework.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Cliffe
 * @Date: 2022-12-01 9:33 上午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    String username;

    int age;

}
