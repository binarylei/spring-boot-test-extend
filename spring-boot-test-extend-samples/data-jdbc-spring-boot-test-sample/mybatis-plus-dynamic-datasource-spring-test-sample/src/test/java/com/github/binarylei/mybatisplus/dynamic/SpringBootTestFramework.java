package com.github.binarylei.mybatisplus.dynamic;

import com.github.binarylei.mybatisplus.dynamic.config.DynamicDataSourceConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author binarylei
 * @version 2020-11-14
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DynamicDataSourceConfig.class})
public abstract class SpringBootTestFramework {

}
