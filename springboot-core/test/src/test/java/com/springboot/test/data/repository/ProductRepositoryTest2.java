package com.springboot.test.data.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.test.data.entity.Product;

@SpringBootTest
public class ProductRepositoryTest2 {

  @Autowired
  private ProductRepository productRepository;

  @Test
  public void basicCRUDTest() {
    // <create>

    // given
    Product product = Product.builder()
        .name("노트")
        .price(1000)
        .stock(500)
        .build();

    // when
    Product savedProduct = productRepository.save(product);

    // then
    assertEquals(product.getNumber(), savedProduct.getNumber());
    assertEquals(product.getName(), savedProduct.getName());
    assertEquals(product.getPrice(), savedProduct.getPrice());
    assertEquals(product.getStock(), savedProduct.getStock());

    // <read>

    // when
    Product selectedProduct = productRepository.findById(savedProduct.getNumber()).orElseThrow(RuntimeException::new);

    // then
    assertEquals(product.getNumber(), selectedProduct.getNumber());
    assertEquals(product.getName(), selectedProduct.getName());
    assertEquals(product.getPrice(), selectedProduct.getPrice());
    assertEquals(product.getStock(), selectedProduct.getStock());

    // <update>

    // when
    Product foundProduct = productRepository.findById(savedProduct.getNumber()).orElseThrow(RuntimeException::new);

    foundProduct.setName("장난감");

    Product updatedProduct = productRepository.save(foundProduct);

    // then
    assertEquals("장난감", updatedProduct.getName());

    // <delete>

    // when
    productRepository.delete(updatedProduct);

    // then
    assertFalse(productRepository.findById(selectedProduct.getNumber()).isPresent());
  }
}
