package com.github.binarylei.mybatisplus.dynamic.config;

import com.github.binarylei.test.autoconfigure.mybatisplus.MybatisPlusDynamicDataSourceTest;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author binarylei
 * @version 2020-11-14
 */
@MybatisPlusDynamicDataSourceTest
@MapperScan(basePackages = "com.github.binarylei.mybatisplus.dynamic.mapper",
        annotationClass = Mapper.class)
@ComponentScan("com.github.binarylei.mybatisplus.dynamic.service")
public class DynamicDataSourceConfig {

//    @Bean
//    @ConditionalOnMissingBean
//    public PlatformTransactionManager transactionManager(DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
}
