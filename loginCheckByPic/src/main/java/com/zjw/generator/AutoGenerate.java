package com.zjw.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class AutoGenerate {
    public static void main(String[] args) {
        FastAutoGenerator.create(
                        "jdbc:mysql://121.36.41.66:3506/testdb",
                        "root",
                        "MaYEsT.qTl12G3/@*")
                .globalConfig(builder -> {
                    builder.author("zjw") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
//                            .fileOverride() // 覆盖已生成文件

                            .disableOpenDir() //禁止打开输出目录
                            .outputDir( "F:\\software\\IDEA\\project\\javaProjectExample\\loginCheckByPic\\src\\main\\java"); // 指定输出目录
                })
                // 覆盖已生成文件
//                .strategyConfig(builder -> {
//                    builder.entityBuilder().enableFileOverride();
//                    builder.mapperBuilder().enableFileOverride();
//                    builder.serviceBuilder().enableFileOverride();
//                    builder.controllerBuilder().enableFileOverride();
//
//
//                })
                .packageConfig(builder -> {
                    builder.parent("com.zjw") // 设置父包名
                            .controller("controller")
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("mapper")
//                            .xml("mapper")  //正常的xml
                            .entity("model") //设置entity包名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "F:\\software\\IDEA\\project\\javaProjectExample\\loginCheckByPic\\src\\main\\resources\\sql\\mapper")); // 设置mapperXml生成路径

                })

//               表的配置
                .strategyConfig(builder -> {
                    builder.addInclude("sys_config");  //设置生成的表名字
                })
                .templateEngine(new FreemarkerTemplateEngine()) //使用..模板
                .execute();
        System.out.println("成功了.....");
    }
}
