package com.github.binarylei.mybatis.dynamic;

import com.gitbub.binarylei.model.User;
import com.github.binarylei.mybatis.dynamic.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * @author binarylei
 * @version 2020-11-14
 */
@SpringBootApplication
public class DynamicDataSourceApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DynamicDataSourceApplication.class);

        UserService userService = context.getBean(UserService.class);
        List<User> users1 = userService.user1();
        List<User> users2 = userService.user2();
        System.out.println(users1);
        System.out.println(users2);
    }
}
