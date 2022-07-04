package com.book.common.conf;


import com.book.common.aspect.LoginAuthAspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.*;

import java.util.Arrays;
import java.util.List;

/***
 * @author wangyh
 * @date 2018/12/23
 * @deprecated spring boot 静态文件配置文件
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 如果我们将/xxxx/** 修改为 /** 与默认的相同时，则会覆盖系统的配置，可以多次使用 addResourceLocations 添加目录，
     * 优先级先添加的高于后添加的。
     * <p>
     * 如果是/xxxx/** 引用静态资源 加不加/xxxx/ 均可，因为系统默认配置（/**）也会作用
     * 如果是/** 会覆盖默认配置，应用addResourceLocations添加所有会用到的静态资源地址，系统默认不会再起作用
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
        super.addResourceHandlers(registry);
    }

    /***
     * 修改默认启动跳转首页
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }

    /***
     * 拦截器配置
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> EXCLUDE_PATH = Arrays.asList(
                "/", "/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.gif", "/**/*.map",
                "/index", "/error",
                "/loginCont/userLogin",
                "/loginCont/logout",
                "/loginCont/getVeriCodeImg");
        registry.addInterceptor(new LoginAuthAspect()).addPathPatterns("/**").excludePathPatterns(EXCLUDE_PATH);
    }
}
