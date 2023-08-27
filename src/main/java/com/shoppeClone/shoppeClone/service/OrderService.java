package com.shoppeClone.shoppeClone.service;

import java.util.List;

import com.shoppeClone.shoppeClone.dto.order.CreateOrderDTO;
import com.shoppeClone.shoppeClone.dto.order.OrderDTO;


public interface OrderService {
	
	OrderDTO createOrder(CreateOrderDTO dto);
	
	OrderDTO UpdateOrder(Long orderId,CreateOrderDTO dto);
	
	void deleteOrder(Long orderId);
	
	List<OrderDTO> getOrder();
	
}
