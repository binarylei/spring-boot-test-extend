package com.github.binarylei.test.autoconfigure.mybatisplus.dynamic;

import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceAutoConfiguration;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceCreatorAutoConfiguration;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.druid.DruidDynamicDataSourceConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 对 Mybatis-plus 提供的动态数据进行扩展：<p>
 * <li>1. 注册动态数据源都到 Spring 上下文中，其中 bean 的名称为动态数据源的 key</li>
 * <li>2. 注册动态数据源对应的事务管理器，名称为 beanName + "TransactionManager"</li>
 *
 * @author binarylei
 * @version 2020-11-14
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(DynamicDataSourceProperties.class)
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
@Import(value = {DruidDynamicDataSourceConfiguration.class, DynamicDataSourceCreatorAutoConfiguration.class})
@ConditionalOnProperty(prefix = DynamicDataSourceProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class MybatisPlusDynamicDataSourceAutoConfiguration extends DynamicDataSourceAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public PlatformTransactionManager platformTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    public MybatisPlusDynamicDataSourceAutoConfiguration(DynamicDataSourceProperties properties) {
        super(properties);
    }
//
//    @Bean
//    @Primary
//    @ConditionalOnMissingBean
//    @Override
//    public DataSource dataSource(DynamicDataSourceProvider dynamicDataSourceProvider) {
//        registerSubDataSource(dynamicDataSourceProvider, beanFactory);
//
//        return super.dataSource(dynamicDataSourceProvider);
//    }
//
//    private void registerSubDataSource(DynamicDataSourceProvider dynamicDataSourceProvider,
//            ConfigurableBeanFactory beanFactory) {
//        Map<String, DataSource> dataSourceMap = dynamicDataSourceProvider.loadDataSources();
//        dataSourceMap.forEach((beanName, datasource) -> registerSubDataSource(
//                beanName, datasource, beanFactory));
//    }
//
//    // 添加单个Datasource和其对应的事务处理器TransactionManager
//    private void registerSubDataSource(String beanName, DataSource datasource,
//            ConfigurableBeanFactory beanFactory) {
//        // 注册datasource
//        beanFactory.registerSingleton(beanName, datasource);
//        // 注册事务管理器
//        beanFactory.registerSingleton(beanName + "TransactionManager",
//                new DataSourceTransactionManager(datasource));
//    }
//
//    @Override
//    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
//        this.beanFactory = (ConfigurableBeanFactory) beanFactory;
//    }
}
