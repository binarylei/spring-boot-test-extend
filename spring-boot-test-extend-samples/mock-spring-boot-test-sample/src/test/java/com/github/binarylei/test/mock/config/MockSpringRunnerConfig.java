package com.github.binarylei.test.mock.config;

import com.github.binarylei.test.autoconfigure.mybatisplus.AutoConfigureMybatisPlus;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

/**
 * @author binarylei
 * @version 2020-11-21
 */
@Configuration
@AutoConfigureMybatisPlus
@MapperScan(basePackages = "com.github.binarylei.test.mock.mapper",
        annotationClass = Mapper.class)
public class MockSpringRunnerConfig {

    @Bean
    public URI url() {
        return URI.create("http://127.0.0.1");
    }
}
