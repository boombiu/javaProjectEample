package com.zjw.controller;

import cn.hutool.core.util.IdUtil;
import com.google.code.kaptcha.Producer;
import com.zjw.comminutils.utils.RedisUtil;
import com.zjw.constants.R;
import com.zjw.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.zjw.comminutils.constants.Constants.CAPTCHA_CACHE;

@RestController
@CrossOrigin
public class CaptchaController {

    @Autowired
    private RedisUtil redisUtil;
    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @GetMapping("/captchaImage")
    public R getCode() {
        R r = new R();
        String captchaImage = null;
        String code = null;
        try {
            code = captchaProducer.createText();
            BufferedImage image = captchaProducer.createImage(code);
            // 转换流信息写出
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", os);
            captchaImage = Base64.getEncoder().encodeToString(os.toByteArray());

        } catch (Exception e) {
            e.printStackTrace();
            return new R("01", "生成验证码失败", null);
        }
        String uuid = IdUtil.fastSimpleUUID();
        String verifyKey = CAPTCHA_CACHE + uuid;
        boolean set = redisUtil.set(verifyKey, code, 2, TimeUnit.MINUTES);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("image",captchaImage);
        resultMap.put("uuid",uuid);
        r.setCode("00");
        r.setMsg("生成验证码成功");
        r.setData(resultMap);
        return r;

    }
}
