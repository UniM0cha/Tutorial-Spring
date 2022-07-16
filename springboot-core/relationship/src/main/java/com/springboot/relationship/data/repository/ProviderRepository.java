package com.springboot.relationship.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.relationship.data.entity.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {

}
