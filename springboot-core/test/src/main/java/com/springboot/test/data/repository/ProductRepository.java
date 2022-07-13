package com.springboot.test.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.test.data.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
