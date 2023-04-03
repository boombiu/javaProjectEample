package com.zjw.service.impl;

import com.zjw.model.SysConfig;
import com.zjw.mapper.SysConfigMapper;
import com.zjw.service.ISysConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    @Override
    public Boolean getCaptchaEnable(String key) {
        return null;
    }
}
