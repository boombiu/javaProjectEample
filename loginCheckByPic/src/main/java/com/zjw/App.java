package com.zjw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@MapperScan("com.zjw.mapper")
@SpringBootApplication
public class App {
    public static void main( String[] args )    {
        System.out.println( "Hello World!" );
        try {
            SpringApplication.run(App.class, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(" =dsfa ");
    }
}
