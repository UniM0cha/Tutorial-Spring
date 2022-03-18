// package com.example.javajava.configuration;

// import com.zaxxer.hikari.HikariConfig;
// import com.zaxxer.hikari.HikariDataSource;
// import org.apache.ibatis.session.SqlSessionFactory;
// import org.mybatis.spring.SqlSessionFactoryBean;
// import org.mybatis.spring.SqlSessionTemplate;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.context.ApplicationContext;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.PropertySource;

// import java.util.Properties;

// import javax.sql.DataSource;

// @Configuration
// @PropertySource("classpath:/application.yml")
// public class DataBaseConfiguration {

// // 빈과 같이 ApplicationContext를 메모리에 올려준다.
// @Autowired
// private ApplicationContext applicationContext;

// // 빈으로 만들어서 주입시킨다
// @Bean
// @ConfigurationProperties(prefix = "spring.datasource.hikari")
// public HikariConfig hikariConfig() {
// return new HikariConfig();
// }

// @Bean
// public DataSource dataSource() throws Exception {
// DataSource dataSource = new HikariDataSource(hikariConfig());
// System.out.println("dataSource = " + dataSource.toString());
// return dataSource;
// }

// // SqlSessionFactory 세팅
// @Bean
// public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws
// Exception {
// SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

// // 데이터소스 넣어줌
// sqlSessionFactoryBean.setDataSource(dataSource);

// // 매퍼의 위치 넣어줌
// sqlSessionFactoryBean.setMapperLocations(
// applicationContext.getResources("classpath:mapper/**/sql-*.xml"));

// // Mybatis 자체 설정
// sqlSessionFactoryBean.setConfiguration(mybatisConfig());

// return sqlSessionFactoryBean.getObject();
// }

// // application.yml에서 마이바티스 설정 받아옴
// @Bean
// @ConfigurationProperties(prefix = "mybatis.configuration")
// public org.apache.ibatis.session.Configuration mybatisConfig() {
// return new org.apache.ibatis.session.Configuration();
// }

// // SqlSessionTemplate 설정
// @Bean
// public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory
// sqlSessionFactory) {
// return new SqlSessionTemplate(sqlSessionFactory);
// }

// /**
// * 1. 개요 : JPA 설정
// * 2. 처리내용 : JPA 설정 빈 등록
// *
// * @Method Name : hibernateConfig
// * @return
// */
// @Bean
// @ConfigurationProperties(prefix = "spring.jpa")
// public Properties hibernateConfig() {
// return new Properties();
// }
// }
