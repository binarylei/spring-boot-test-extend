package com.github.binarylei.test.mock.service.impl;

import com.github.binarylei.test.mock.mapper.UserMapper;
import com.github.binarylei.test.mock.service.MenuService;
import com.github.binarylei.test.mock.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;

/**
 * @author binarylei
 * @version 2020-11-21
 */
@Data
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuService menuTestService;

    @Autowired
    private URI uri;
}
