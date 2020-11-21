package com.github.binarylei.test.runner.service.impl;

import com.github.binarylei.test.runner.mapper.UserTestBeanMapper;
import com.github.binarylei.test.runner.service.MenuTestService;
import com.github.binarylei.test.runner.service.UserTestBeanService;
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
public class UserTestBeanServiceImpl implements UserTestBeanService {

    @Autowired
    private UserTestBeanMapper userTestBeanMapper;
    @Autowired
    private MenuTestService menuTestService;

    @Autowired
    private URI uri;
}
