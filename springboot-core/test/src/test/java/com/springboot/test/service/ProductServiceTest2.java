package com.springboot.test.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.springboot.test.data.dto.ProductDto;
import com.springboot.test.data.dto.ProductResponseDto;
import com.springboot.test.data.entity.Product;
import com.springboot.test.data.repository.ProductRepository;
import com.springboot.test.service.impl.ProductServiceImpl;

@ExtendWith(SpringExtension.class)
@Import({ ProductServiceImpl.class })
public class ProductServiceTest2 {

  @MockBean
  private ProductRepository productRepository;

  @Autowired
  private ProductService productService;

  @BeforeEach
  public void setUpTest() {
    productService = new ProductServiceImpl(productRepository);
  }

  @Test
  void testGetProduct() {
    Product givenProduct = new Product();
    givenProduct.setNumber(123L);
    givenProduct.setName("펜");
    givenProduct.setPrice(1000);
    givenProduct.setStock(1234);

    Mockito.when(productRepository.findById(123L)).thenReturn(Optional.of(givenProduct));

    ProductResponseDto productResponseDto = productService.getProduct(123L);

    Assertions.assertEquals(givenProduct.getNumber(), productResponseDto.getNumber());
    Assertions.assertEquals(givenProduct.getName(), productResponseDto.getName());
    Assertions.assertEquals(givenProduct.getPrice(), productResponseDto.getPrice());
    Assertions.assertEquals(givenProduct.getStock(), productResponseDto.getStock());

    verify(productRepository).findById(123L);
  }

  @Test
  void testSaveProduct() {
    Mockito.when(productRepository.save(any(Product.class))).then(AdditionalAnswers.returnsFirstArg());

    ProductResponseDto productResponseDto = productService.saveProduct(new ProductDto("펜", 1000, 1234));

    Assertions.assertEquals("펜", productResponseDto.getName());
    Assertions.assertEquals(1000, productResponseDto.getPrice());
    Assertions.assertEquals(1234, productResponseDto.getStock());

    verify(productRepository).save(any());
  }
}
