package com.cos.jwt.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cos.jwt.filter.MyFilter1;

@Configuration
public class FilterConfig {

  @Bean
  public FilterRegistrationBean<MyFilter1> filter1() {
    FilterRegistrationBean<MyFilter1> bean = new FilterRegistrationBean<>(new MyFilter1());
    bean.addUrlPatterns("/*"); // 모든 경로에 대해서 필터 수행
    bean.setOrder(0); // 낮은 번호가 필터 중에서 가장 먼저 실행됨
    return bean;
  }

}
