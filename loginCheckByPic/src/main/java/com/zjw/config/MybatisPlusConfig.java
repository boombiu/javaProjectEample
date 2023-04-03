package com.zjw.config;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 分页插件
 */

@Configuration
//继承整个分页类，然后不让他回去第一页，返回上一页
public class MybatisPlusConfig extends PaginationInnerInterceptor {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        // 创建分页插件
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor PaginationInnerInterceptor = new MybatisPlusConfig();
        PaginationInnerInterceptor.setOverflow(true);
        interceptor.addInnerInterceptor(PaginationInnerInterceptor);
        return interceptor;
        // 设置操作的数据 设置数据库类型
//        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));

        //        设置分页插件，超出范围时返回上一页

//        重写分页跳转的方法，不让他返回第一页，让他去 -1 页
//        NewPaginationInnerInterceptor paginationInnerInterceptor = new NewPaginationInnerInterceptor(DbType.MYSQL);
//        paginationInnerInterceptor.setOverflow(true);
//        interceptor.addInnerInterceptor(paginationInnerInterceptor);
//        return interceptor;
    }
    @Override
    protected void handlerOverflow(IPage<?> page) {
        page.setCurrent(page.getCurrent() -1 );
    }
}
