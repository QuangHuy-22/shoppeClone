package com.shoppeClone.shoppeClone.respository.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppeClone.shoppeClone.entity.OrderEntity;

public interface OrderRepostory extends JpaRepository<OrderEntity, Long> {

}
