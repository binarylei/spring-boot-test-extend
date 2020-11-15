package com.github.binarylei.mybatis.dynamic.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author binarylei
 * @version 2020-11-14
 */
@Configuration
public class MasterDataSourceConfig {

    /**
     * {@link @ConfigurationProperties} 直接将 "spring.datasource.master" 的属性绑定到 HikariDataSource
     *
     */
    @Bean("master")
    @Primary
    @ConditionalOnMissingBean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource master() {
        return new HikariDataSource();
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean
    public PlatformTransactionManager masterTransactionManager(
            @Qualifier("master") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean
    public SqlSessionTemplate masterSqlSessionTemplate(
            @Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean
    public SqlSessionFactoryBean masterSqlSessionFactory(
            @Qualifier("master") DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

}
