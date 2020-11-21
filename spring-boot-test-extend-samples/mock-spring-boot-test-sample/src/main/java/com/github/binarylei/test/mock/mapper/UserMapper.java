package com.github.binarylei.test.mock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gitbub.binarylei.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author binarylei
 * @version 2020-11-21
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
