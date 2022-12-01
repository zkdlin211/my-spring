package com.cliffe.springframework.test.entity;

import lombok.Data;

/**
 * @Author: Cliffe
 * @Date: 2022-12-01 10:45 上午
 */
@Data
public class UserService {

    private UserDao userDao;

    private String accessKey;

    public String getUserInfo(String userId){
        return userDao.queryUserByName(userId);
    }
}
