package com.github.binarylei.mybatis.test.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;

/**
 * @author binarylei
 * @version 2020-11-12
 */
@MybatisTest
@MapperScan(basePackages = "com.github.binarylei.mybatis.test.mapper",
        annotationClass = Mapper.class)
public class MybatisConfig {

}
