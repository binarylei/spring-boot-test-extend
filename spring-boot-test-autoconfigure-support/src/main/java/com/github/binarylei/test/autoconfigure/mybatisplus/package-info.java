package com.github.binarylei.test.autoconfigure.mybatisplus;

/**
 * {@link @MybatisPlusTest}                     mybatis-plus
 * {@link @MybatisPlusDynamicDataSourceTest}    动态数据源
 *
 * <pre>
 * spring:
 *   datasource:
 *     dynamic:
 *       primary: master
 *       datasource:
 *         master:
 *           driver-class-name: org.h2.Driver
 *           # url必须的引号，否则会解析错误
 *           url: "jdbc:h2:mem:master;INIT=runscript from 'classpath:sql/master/schema.sql'\\;runscript from 'classpath:sql/master/data.sql'"
 *           password: root
 *           username: root
 *         slave:
 *           driver-class-name: org.h2.Driver
 *           url: "jdbc:h2:mem:slave;INIT=runscript from 'classpath:sql/slave/schema.sql'\\;runscript from 'classpath:sql/slave/data.sql'"
 *           password: root
 *           username: root
 * </pre>
 */