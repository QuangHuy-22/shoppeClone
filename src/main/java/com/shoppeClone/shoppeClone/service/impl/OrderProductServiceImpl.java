package com.shoppeClone.shoppeClone.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppeClone.shoppeClone.converter.orderProduct.OrderProductConverter;
import com.shoppeClone.shoppeClone.dto.orderProduct.OrderProductDTO;
import com.shoppeClone.shoppeClone.entity.OrderProductEntity;
import com.shoppeClone.shoppeClone.respository.orderProduct.OrderProductRepository;
import com.shoppeClone.shoppeClone.service.OrderProductService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderProductServiceImpl implements OrderProductService{

	@Autowired
	private OrderProductRepository orderProductRepository;
	
	@Autowired
	private OrderProductConverter orderProductConverter;
	
	@Override
	public OrderProductDTO createOrderProduct(OrderProductDTO dto) {

	
		
		return null;
	}

}
