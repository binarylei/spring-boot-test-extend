package com.github.binarylei.mybatisplus.mapper;

import com.gitbub.binarylei.model.User;
import com.github.binarylei.mybatisplus.annotation.MasterDao;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author binarylei
 * @version 2020-11-12
 */
@MasterDao
public interface MasterUserMapper {

    @Insert("insert into user values(#{user.id}, #{user.name})")
    void insertUser(@Param("user") User user);

    @Select("select * from user where id=#{id}")
    User selectUserById(@Param("id") long id);
}
