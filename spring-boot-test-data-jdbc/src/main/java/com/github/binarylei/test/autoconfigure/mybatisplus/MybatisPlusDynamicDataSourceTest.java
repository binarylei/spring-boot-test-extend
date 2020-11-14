package com.github.binarylei.test.autoconfigure.mybatisplus;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author binarylei
 * @version 2020-11-09
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@OverrideAutoConfiguration(enabled = false)
@Transactional
@AutoConfigureCache
@AutoConfigureDynamicDataSource
@ImportAutoConfiguration
public @interface MybatisPlusDynamicDataSourceTest {
}
