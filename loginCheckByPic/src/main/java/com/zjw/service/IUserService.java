package com.zjw.service;

import com.zjw.constants.R;
import com.zjw.model.LoginBody;
import com.zjw.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zjw
 * @since 2023-02-21
 */
public interface IUserService extends IService<User> {


    R login(@RequestBody LoginBody loginBody);

    String register(User user);
}
