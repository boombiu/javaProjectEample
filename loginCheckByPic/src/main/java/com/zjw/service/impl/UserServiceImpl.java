package com.zjw.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjw.comminutils.constants.Constants;
import com.zjw.comminutils.utils.RedisUtil;
import com.zjw.constants.R;
import com.zjw.model.LoginBody;
import com.zjw.model.User;
import com.zjw.mapper.UserMapper;
import com.zjw.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2023-02-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public R login(@RequestBody LoginBody loginBody) {
        String username = loginBody.getUserName();
        String code = loginBody.getCode();
        String uuid = IdUtil.fastSimpleUUID();
        //先校验验证码
        Boolean captchaVerify = validateCaptcha(code, uuid);
        if (!captchaVerify) {
            return R.faild();
        }

        QueryWrapper<User> userQR = new QueryWrapper<>();
        userQR.eq("USER_NAME", loginBody.getUserName());
        User userInDb = userMapper.selectOne(userQR);
        if (userInDb != null && loginBody.getUserPassword().equals(userInDb.getUserPassword())) {
            return R.ok();
        }
        return R.faild();
    }

    private Boolean validateCaptcha(String code, String uuid) {
        String codeCache = Convert.toStr(redisUtil.get(Constants.CAPTCHA_CACHE + uuid));
        return !(StrUtil.isEmptyIfStr(codeCache) | !code.equals(codeCache));

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
        if (insert > 0) {
            return "注册成功";
        }
        return "注册失败";
    }
}
