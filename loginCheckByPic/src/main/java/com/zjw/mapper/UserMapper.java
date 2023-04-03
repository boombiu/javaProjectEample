package com.zjw.mapper;

import com.zjw.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zjw
 * @since 2023-02-21
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
