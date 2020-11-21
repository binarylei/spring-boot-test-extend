package com.github.binarylei.test.runner.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author binarylei
 * @version 2020-11-21
 */
@Data
@TableName("user")
public class UserTestBean {

    private int id;
    private String name;
}
