package com.springboot.test.data.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.springboot.test.data.entity.Product;

@DataJpaTest
// 추가해주면 애플리케이션에서 실제로 사용하는 데이터베이스로 테스트가 가능
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductRepositoryTest {

  @Autowired
  private ProductRepository productRepository;

  @Test
  void saveTest() {
    // given
    Product product = new Product();
    product.setName("펜");
    product.setPrice(1000);
    product.setStock(1000);

    // when
    Product savedProduct = productRepository.save(product);

    // then
    assertEquals(product.getName(), savedProduct.getName());
    assertEquals(product.getPrice(), savedProduct.getPrice());
    assertEquals(product.getStock(), savedProduct.getStock());
  }

  @Test
  void selectTest() {
    // given
    Product product = new Product();
    product.setName("펜");
    product.setPrice(1000);
    product.setStock(1000);

    Product savedProduct = productRepository.saveAndFlush(product);

    // when
    Product foundProduct = productRepository.findById(savedProduct.getNumber()).get();

    // then
    assertEquals(product.getName(), foundProduct.getName());
    assertEquals(product.getPrice(), foundProduct.getPrice());
    assertEquals(product.getStock(), foundProduct.getStock());
  }
}
