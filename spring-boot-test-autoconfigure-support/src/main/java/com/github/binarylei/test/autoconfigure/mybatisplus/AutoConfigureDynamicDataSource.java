package com.github.binarylei.test.autoconfigure.mybatisplus;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * mybatis-plus 多数据源单元装配
 *
 * @author binarylei
 * @version 2020-11-09
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ImportAutoConfiguration
public @interface AutoConfigureDynamicDataSource {
}
