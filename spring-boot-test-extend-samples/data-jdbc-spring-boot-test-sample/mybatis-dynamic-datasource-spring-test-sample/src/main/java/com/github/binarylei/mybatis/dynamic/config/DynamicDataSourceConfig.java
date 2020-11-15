package com.github.binarylei.mybatis.dynamic.config;

import com.github.binarylei.mybatis.dynamic.annotation.MasterDao;
import com.github.binarylei.mybatis.dynamic.annotation.SlaveDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author binarylei
 * @version 2020-11-14
 */
@Configuration
@EnableConfigurationProperties
@Import({MasterDataSourceConfig.class, SlaveDataSourceConfig.class})
@MapperScan(basePackages = "com.github.binarylei.mybatis.dynamic.mapper",
        annotationClass = MasterDao.class, sqlSessionFactoryRef = "masterSqlSessionFactory")
@MapperScan(basePackages = "com.github.binarylei.mybatis.dynamic.mapper",
        annotationClass = SlaveDao.class, sqlSessionFactoryRef = "slaveSqlSessionFactory")
public class DynamicDataSourceConfig {
}
