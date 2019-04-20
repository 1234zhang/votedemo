package com.example.votedemo.config;

import com.example.votedemo.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;

/**
 * @author Brandon.
 * @date 2019/4/16.
 * @time 14:16.
 */

@Configuration
public class LoginFilterConfig {
    @Bean
    public Filter LoginFilter(){
        return new LoginFilter();
    }
    @Bean
    public FilterRegistrationBean identityRegistration(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new DelegatingFilterProxy("LoginFilter"));
        filterRegistrationBean.addUrlPatterns("/vote/*");
        filterRegistrationBean.setName("LoginFilter");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }
}
