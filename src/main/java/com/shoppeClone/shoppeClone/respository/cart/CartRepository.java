package com.shoppeClone.shoppeClone.respository.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoppeClone.shoppeClone.entity.CartEntity;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {

    

}