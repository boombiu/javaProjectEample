package com.zjw.service;

import com.zjw.model.SysConfig;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统配置 服务类
 * </p>
 *
 * @author zjw
 * @since 2023-04-02
 */
public interface ISysConfigService extends IService<SysConfig> {

    Boolean getCaptchaEnable();

    String getConfigByKey(String key);
}
