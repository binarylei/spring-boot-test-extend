package com.github.binarylei.mybatis.dynamic;

import com.github.binarylei.mybatis.dynamic.config.DynamicDataSourceConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author binarylei
 * @version 2020-11-15
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DynamicDataSourceConfig.class})
public abstract class SpringBootTestFramework {
}
