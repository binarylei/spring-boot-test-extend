package com.github.binarylei.mybatis.test.mapper;

import com.github.binarylei.mybatis.test.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author binarylei
 * @version 2020-11-12
 */
@Mapper
public interface UserMapper {

    @Insert("insert into user values(#{user.id}, #{user.name})")
    void insertUser(@Param("user") User user);

    @Select("select * from user where id=#{id}")
    User selectUserById(@Param("id") long id);
}
