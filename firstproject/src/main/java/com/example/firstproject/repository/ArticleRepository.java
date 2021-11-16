package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;

import org.springframework.data.repository.CrudRepository;

// 첫번째 인자 : 관리대상 엔티티
// 두번재 인자 : 대표값의 타입
public interface ArticleRepository extends CrudRepository<Article, Long> {
  
}
