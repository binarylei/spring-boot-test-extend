package com.github.binarylei.mybatisplus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author binarylei
 * @version 2020-11-12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private long id;
    private String name;
}
