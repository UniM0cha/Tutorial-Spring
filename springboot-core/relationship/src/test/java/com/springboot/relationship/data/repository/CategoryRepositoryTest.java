package com.springboot.relationship.data.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.relationship.data.entity.Category;
import com.springboot.relationship.data.entity.Product;

@SpringBootTest
public class CategoryRepositoryTest {
  @Autowired
  ProductRepository productRepository;

  @Autowired
  CategoryRepository categoryRepository;

  @Test
  void relationshipTest() {

    // 테스트 데이터 생성
    Product product = new Product();
    product.setName("가위");
    product.setPrice(5000);
    product.setStock(500);

    productRepository.save(product);

    Category category = new Category();
    category.setCode("S1");
    category.setName("도서");
    category.getProducts().add(product);

    categoryRepository.save(category);

    // 테스트
    List<Product> products = categoryRepository.findById(1L).get().getProducts();
    for (Product foundProduct : products) {
      System.out.println(foundProduct);
    }
  }
}
