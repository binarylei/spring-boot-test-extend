package com.github.binarylei.mybatisplus.config;

import com.github.binarylei.test.autoconfigure.mybatisplus.MybatisPlusTest;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

/**
 * @author binarylei
 * @version 2020-11-12
 */
@MybatisPlusTest
@MapperScan(basePackages = "com.github.binarylei.mybatisplus.mapper",
        annotationClass = Mapper.class)
public class MybatisPlusConfig {
}
