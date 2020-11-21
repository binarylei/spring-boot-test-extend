package com.github.binarylei.test.runner.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.binarylei.test.runner.model.UserTestBean;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author binarylei
 * @version 2020-11-21
 */
@Mapper
public interface UserTestBeanMapper extends BaseMapper<UserTestBean> {
}
