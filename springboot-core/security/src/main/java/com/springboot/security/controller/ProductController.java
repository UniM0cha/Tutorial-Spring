package com.springboot.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.security.data.dto.ChangeProductNameDto;
import com.springboot.security.data.dto.ProductDto;
import com.springboot.security.data.dto.ProductResponseDto;
import com.springboot.security.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/product")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping()
  public ResponseEntity<ProductResponseDto> getProduct(Long number) {
    ProductResponseDto productResponseDto = productService.getProduct(number);

    return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
  }

  @PostMapping()
  public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductDto productDto) {
    ProductResponseDto productResponseDto = productService.saveProduct(productDto);

    return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
  }

  @PutMapping()
  public ResponseEntity<ProductResponseDto> changeProductName(@RequestBody ChangeProductNameDto changeProductNameDto)
      throws Exception {
    ProductResponseDto productResponseDto = productService.changeProductName(
        changeProductNameDto.getNumber(),
        changeProductNameDto.getName());

    return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
  }

  @DeleteMapping()
  public ResponseEntity<ProductResponseDto> changeProductName(@RequestBody ProductDto productDto) {
    ProductResponseDto productResponseDto = productService.saveProduct(productDto);

    return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
  }

}