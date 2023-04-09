package com.zjw.controller;

import cn.hutool.core.util.IdUtil;
import com.google.code.kaptcha.Producer;
import com.zjw.comminutils.utils.RedisUtil;
import com.zjw.constants.RespResult;
import com.zjw.enums.ErrorCode;
import com.zjw.enums.RespCode;
import com.zjw.enums.SuccessCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
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
    public RespResult getCode() {
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
            return new RespResult(ErrorCode.CREAT_CAPTCHA_FAILED);
        }
        String uuid = IdUtil.fastSimpleUUID();
        String verifyKey = CAPTCHA_CACHE + uuid;
        boolean set = redisUtil.set(verifyKey, code, 2, TimeUnit.MINUTES);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("image",captchaImage);
        resultMap.put("uuid",uuid);

        return new RespResult(SuccessCode.CREAT_CAPTCHA_SUCCESS,resultMap);

    }
}
