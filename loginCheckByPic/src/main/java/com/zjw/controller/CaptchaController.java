package com.zjw.controller;

import com.google.code.kaptcha.Producer;
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

@RestController
@CrossOrigin
public class CaptchaController {
    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @GetMapping("/captchaImage")
    public R getCode() {
        R r = new R();
        String captchaImage = "";
        try {
            String code = captchaProducer.createText();
            BufferedImage image = captchaProducer.createImage(code);
            // 转换流信息写出
            ByteArrayOutputStream os = new ByteArrayOutputStream();


            ImageIO.write(image, "jpg", os);

            captchaImage = Base64.getEncoder().encodeToString(os.toByteArray());

        } catch (Exception e) {
            e.printStackTrace();
            return new R("01", "读取失败", null);
        }
        r.setCode("00");
        r.setMsg("读取成功");
        r.setData(captchaImage);

        return r;

    }
}
