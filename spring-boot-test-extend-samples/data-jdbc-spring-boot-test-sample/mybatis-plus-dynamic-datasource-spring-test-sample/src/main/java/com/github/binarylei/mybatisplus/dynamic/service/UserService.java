package com.github.binarylei.mybatisplus.dynamic.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gitbub.binarylei.model.User;
import com.github.binarylei.mybatisplus.dynamic.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author binarylei
 * @version 2020-11-14
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @DS("master")
    public List<User> user1() {
        return userMapper.selectList(new QueryWrapper<>());
    }

    @DS("slave")
    public List<User> user2() {
        return userMapper.selectList(new QueryWrapper<>());
    }
}
