package com.cliffe.springframework.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Cliffe
 * @Date: 2022-12-01 10:06 上午
 */
@Data
@AllArgsConstructor
public class PropertyValue {

    private final String name;

    private final Object value;

}

