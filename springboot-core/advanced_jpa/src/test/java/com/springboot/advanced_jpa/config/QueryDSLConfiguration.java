package com.springboot.advanced_jpa.config;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Configuration
public class QueryDSLConfiguration {

  @PersistenceContext
  EntityManager entityManager;

  // 빈 객체로 등록해놓음으로써 어디서든 사용할 수 있도록 한다.
  @Bean
  public JPAQueryFactory jpaQueryFactory() {
    return new JPAQueryFactory(entityManager);
  }
}
