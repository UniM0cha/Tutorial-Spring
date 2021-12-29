package kr.inhatc.spring.myproject.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 파일처리를 위한 설정
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        // 한번만 만들고 변경할 필요가 없다
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        // 파일의 인코딩 설정
        commonsMultipartResolver.setDefaultEncoding("UTF-8");
        // 파일 당 사이즈 설정 (5MB)
        commonsMultipartResolver.setMaxUploadSizePerFile(5 * 1024 * 1024);
        return commonsMultipartResolver;
    }
}
