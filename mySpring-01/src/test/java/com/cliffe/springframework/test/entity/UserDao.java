package com.cliffe.springframework.test.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Cliffe
 * @Date: 2022-12-01 10:43 上午
 */
public class UserDao {

    private static Map<String, String> map = new HashMap<>();

    static {
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v3");
    }

    public String queryUserByName(String userId){
        return map.get(userId);
    }


}
