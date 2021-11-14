package com.insight.board.configuration;
import java.util.concurrent.ExecutionException;

import javax.sql.DataSource;

import org.apache.catalina.core.ApplicationContext;
import org.apache.ibatis.session.SqlSessionFactory;
import org.hibernate.engine.jdbc.connections.internal.DatasourceConnectionProviderImpl;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:/application.properties")

public class DatabaseConfiguration {
  
  @Autowired
  private ApplicationContext applicationContext;

  @Bean
  @ConfigurationProperties(prefix="spring.datasource.hikari")
  public HikariConfig hikariConfig(){
    return new HikariConfig();
  }

  @Bean
  public DataSource dataSource() throws Exception {
    DataSource dataSource = new HikariDataSource(hikariConfig());
    System.out.println(dataSource.toString());
    return dataSource;
  }

  // @Bean
  // public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
  //   SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
  //   sqlSessionFactoryBean.setDataSource(dataSource);
  // }
}