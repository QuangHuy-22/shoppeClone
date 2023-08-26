package com.shoppeClone.shoppeClone.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppeClone.shoppeClone.converter.order.OrderConverter;
import com.shoppeClone.shoppeClone.dto.order.CreateOrderDTO;
import com.shoppeClone.shoppeClone.dto.order.OrderDTO;
import com.shoppeClone.shoppeClone.entity.OrderEntity;
import com.shoppeClone.shoppeClone.exception.ValidateException;
import com.shoppeClone.shoppeClone.respository.order.OrderRepository;
import com.shoppeClone.shoppeClone.service.OrderService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderConverter orderConverter;
	
	
	@Override
	public OrderDTO createOrder(CreateOrderDTO createOrderDTO) {
//		
//			Long orderId = createOrderDTO.getOrderId();
//	    if (orderId == null) {
//	        throw new ValidateException("Order id cannot be empty");
//	    }

	    // Kiểm tra người dùng trước khi gọi getUserId()
	    if (createOrderDTO.getUserId() == null) {
	        throw new ValidateException("User cannot be null");
	    }
		 	OrderEntity orderEntity = orderConverter.toEntity(createOrderDTO);
	        orderRepository.save(orderEntity);

	        OrderDTO resultDTO = orderConverter.toDTO(orderEntity);
	        return resultDTO;
	}
	
}
