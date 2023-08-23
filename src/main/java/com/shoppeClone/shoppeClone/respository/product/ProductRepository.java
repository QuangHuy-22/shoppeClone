package com.shoppeClone.shoppeClone.respository.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppeClone.shoppeClone.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
