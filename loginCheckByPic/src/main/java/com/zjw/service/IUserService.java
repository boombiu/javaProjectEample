package com.zjw.service;

import com.zjw.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zjw
 * @since 2023-02-21
 */
public interface IUserService extends IService<User> {

    String login(User user);
    String register(User user);
}
