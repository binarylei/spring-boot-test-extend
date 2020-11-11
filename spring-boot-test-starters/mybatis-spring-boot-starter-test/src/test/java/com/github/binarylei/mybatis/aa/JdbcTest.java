package com.github.binarylei.mybatis.aa;

import com.github.binarylei.mybatis.config.JdbcConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @author binarylei
 * @version 2020-11-10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JdbcConfig.class)
public class JdbcTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test() {
        jdbcTemplate.execute("insert into user(id) values(1)");

        Map<String, Object> result = jdbcTemplate.queryForMap("select id from user");
        System.out.println(result);
    }
}
