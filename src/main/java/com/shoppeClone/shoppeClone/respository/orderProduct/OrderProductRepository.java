package com.shoppeClone.shoppeClone.respository.orderProduct;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppeClone.shoppeClone.entity.OrderProductEntity;

public interface OrderProductRepository extends JpaRepository<OrderProductEntity, Long>{

	
}
