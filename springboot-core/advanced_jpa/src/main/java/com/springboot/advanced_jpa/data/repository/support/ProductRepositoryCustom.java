package com.springboot.advanced_jpa.data.repository.support;

import java.util.List;

import com.springboot.advanced_jpa.data.entity.Product;

public interface ProductRepositoryCustom {
  List<Product> findByName(String name);
}
