package com.github.binarylei.mybatisplus.config;

import com.github.binarylei.mybatisplus.annotation.MasterDao;
import com.github.binarylei.mybatisplus.annotation.SlaveDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author binarylei
 * @version 2020-11-14
 */
@Configuration
//@EnableConfigurationProperties(DataSourceProperties.class)
//@Import({MasterDataSourceConfig.class, SlaveDataSourceConfig.class})
//@MapperScan(basePackages = "com.github.binarylei.mybatisplus.mapper",
//        annotationClass = MasterDao.class, sqlSessionFactoryRef = "masterSqlSessionFactory")
//@MapperScan(basePackages = "com.github.binarylei.mybatisplus.mapper",
//        annotationClass = SlaveDao.class, sqlSessionFactoryRef = "slaveSqlSessionFactory")
@MapperScan(basePackages = "com.github.binarylei.mybatisplus.mapper",
        annotationClass = MasterDao.class)
public class DynamicDataSourceConfig {
}
