package com.zjw.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjw.model.SysConfig;
import com.zjw.mapper.SysConfigMapper;
import com.zjw.service.ISysConfigService;
import com.zjw.comminutils.utils.RedisUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统配置 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2023-04-02
 */
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements ISysConfigService {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SysConfigMapper sysConfigMapper;

    /**
     * 获取验证码配置
     * @return
     */
    @Override
    public Boolean getCaptchaEnable() {
        String captchaEnable = getConfigByKey("sys.config.captchaEnabled:");
        if (StrUtil.isBlankIfStr(captchaEnable)) {
            //若没有配置则直接返回true
            return true;
        }
        return Convert.toBool(captchaEnable);
    }

    /**
     * 根据key获取redis或数据库中配置
     * @param key
     * @return
     */
    @Override
    public String getConfigByKey(String key) {
        //获取redis缓存中的数据
        String captchaEnable = Convert.toStr(redisUtil.get(key));

        if (StrUtil.isNotBlank(captchaEnable)){
            return captchaEnable;
        }
        //若redis中没有，则读取数据库中数据并缓存进redis
        QueryWrapper<SysConfig> sysConfigQueryWrapper = new QueryWrapper<>();
        sysConfigQueryWrapper.eq("config_key", key);
        SysConfig sysConfig = sysConfigMapper.selectOne(sysConfigQueryWrapper);
        return sysConfig.getConfigValue();
    }
}
