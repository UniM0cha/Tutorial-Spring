package com.springboot.relationship.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.relationship.data.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  // findBy...
  Optional<Product> findByNumber(Long number);

  List<Product> findAllByName(String name);

  Product queryByNumber(Long number);

  // existsBy...
  boolean existsByNumber(Long number);

  // countBy...
  long countByName(String name);

  // deleteBy...
  void deleteByNumber(Long number);

  long removeByName(String name);

  // First... Top...
  List<Product> findFirst5ByName(String name);

  List<Product> findTop10ByName(String name);

  // is
  Product findByNumberIs(Long number);

  Product findByNumberEquals(Long number);

  // not
  Product findByNumberIsNot(Long number);

  Product findByNumberNot(Long number);

  // null / notnull
  List<Product> findByUpdatedAtNull();

  List<Product> findByUpdatedAtIsNull();

  List<Product> findByUpdatedAtNotNull();

  List<Product> findByUpdatedAtIsNotNull();

  // And / Or
  Product findByNumberAndName(Long number, String name);

  Product findByNumberOrName(Long number, String name);

  // GreaterThan / LessThan / Between
  List<Product> findByPriceIsGreaterThan(Long price);

  List<Product> findByPriceGreaterThan(Long price);

  List<Product> findByPriceGreaterThanEqual(Long price);

  List<Product> findByPriceIsLessThan(Long price);

  List<Product> findByPriceLessThan(Long price);

  List<Product> findByPriceLessThanEqual(Long price);

  List<Product> findByPriceIsBetween(Long lowPrice, Long highPrice);

  List<Product> findByPriceBetween(Long lowPrice, Long highPrice);

  // StartingWith(StartsWith) / EndingWith(EndsWith) / Containing(Contains) / Like
  List<Product> findByNameLike(String name);

  List<Product> findByNameIsLike(String name);

  List<Product> findByNameContains(String name);

  List<Product> findByNameContaining(String name);

  List<Product> findByNameIsContaining(String name);

  List<Product> findByNameStartsWith(String name);

  List<Product> findByNameStartingWith(String name);

  List<Product> findByNameIsStartingWith(String name);

  List<Product> findByNameEndsWith(String name);

  List<Product> findByNameEndingWith(String name);

  List<Product> findByNameIsEndingWith(String name);

  // ASC : 오름차순 / DESC : 내림차순
  List<Product> findByNameOrderByNumberAsc(String name);

  List<Product> findByNameOrderByNumberDesc(String name);

  // 여러 정렬기준
  List<Product> findByNameOrderByPriceAscStockDesc(String name);

  // 매개변수를 활용한 쿼리 정렬
  List<Product> findByName(String name, Sort sort);

  /////////////////
  // 페이징 처리 //
  /////////////////
  Page<Product> findByName(String name, Pageable pageable);

  ////////// @Query //////////
  @Query("SELECT p FROM Product AS p WHERE p.name = ?1")
  List<Product> findByName(String name);

  @Query("SELECT p FROM Product AS p WHERE p.name = :name")
  List<Product> findByNameParam(@Param("name") String name);

  @Query("SELECT p.name, p.price, p.stock FROM Product AS p WHERE p.name = :name")
  List<Object[]> findByNameParam2(@Param("name") String name);
}
