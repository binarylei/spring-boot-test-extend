package com.github.binarylei.mybatis.plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.binarylei.mybatis.plus.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author binarylei
 * @version 2020-11-12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

//    @Select("select * from user where id=#{id}")
//    User selectUserById(@Param("id") long id);
}
