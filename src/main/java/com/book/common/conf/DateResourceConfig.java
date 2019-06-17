package com.book.common.conf;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/***
 * 目前仅easyui拦截器配置
 */

@Component
@MapperScan(basePackages = { "com.fjwing.mapper*" }) // , sqlSessionFactoryRef = "sqlSessionFactory"
public class DateResourceConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
        return page;
    }
}
