package com.springboot.valid_exception.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.valid_exception.data.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
