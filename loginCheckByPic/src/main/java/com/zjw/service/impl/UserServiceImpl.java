package com.zjw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjw.model.User;
import com.zjw.mapper.UserMapper;
import com.zjw.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zjw
 * @since 2023-02-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public String login(User user) {
        QueryWrapper<User> userQR = new QueryWrapper<>();
        userQR.eq("USER_EMAIL",user.getUserEmail());
        User userInDb = userMapper.selectOne(userQR);
        if (userInDb!= null && user.getUserPassword().equals(userInDb.getUserPassword())) {
            return "验证通过";
        }
        return "邮箱或密码错误";
    }


    @Override
    public String register(User user) {
        String userEmail = user.getUserEmail();
        if (userEmail == null || userEmail.equals("")) {
            return "邮箱为必输";
        }
        String userPassword = user.getUserPassword();
        if (userPassword == null || userPassword.equals("")) {
            return "密码为必输";
        }
        int insert = userMapper.insert(user);
        if (insert>0){
            return "注册成功";
        }
        return "注册失败";
    }
}
