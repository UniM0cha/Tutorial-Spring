package kr.inhatc.spring.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

// 첨부파일과 관련된 자동 구성을 사용하지 않도록 설정
// 따로 설정을 했기 때문에 원래 있던 자동 구성을 빼버리는 것.
@SpringBootApplication(exclude = {MultipartAutoConfiguration.class})
public class MyProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyProjectApplication.class, args);
	}

}
