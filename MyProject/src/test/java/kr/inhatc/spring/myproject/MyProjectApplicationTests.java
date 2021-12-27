package kr.inhatc.spring.myproject;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyProjectApplicationTests {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	public void testSqlSessionTest() {
		System.out.println("sqlSessionTemplate = " + sqlSessionTemplate.toString());
	}

}
