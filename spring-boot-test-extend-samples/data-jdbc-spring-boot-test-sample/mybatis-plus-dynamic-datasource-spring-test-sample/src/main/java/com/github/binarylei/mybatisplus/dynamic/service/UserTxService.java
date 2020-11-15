package com.github.binarylei.mybatisplus.dynamic.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gitbub.binarylei.model.User;
import com.github.binarylei.mybatisplus.dynamic.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author binarylei
 * @version 2020-11-14
 */
@Service
@Transactional
@DS("slave")
public class UserTxService extends ServiceImpl<UserMapper, User> {
}
