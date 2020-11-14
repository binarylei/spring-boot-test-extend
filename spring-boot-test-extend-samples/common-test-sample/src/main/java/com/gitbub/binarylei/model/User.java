package com.gitbub.binarylei.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author binarylei
 * @version 2020-11-12
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private long id;
    private String name;
}
