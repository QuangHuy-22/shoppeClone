package com.shoppeClone.shoppeClone.service;

import java.util.List;
import java.util.Map;

import com.shoppeClone.shoppeClone.dto.PageDTO;
import com.shoppeClone.shoppeClone.dto.category.CategoryDTO;
import com.shoppeClone.shoppeClone.dto.order.CreateOrderDTO;
import com.shoppeClone.shoppeClone.dto.order.OrderDTO;


public interface OrderService {
	
	OrderDTO createOrder(CreateOrderDTO dto);
	
	OrderDTO UpdateOrder(Long orderId,CreateOrderDTO dto);
	
	void deleteOrder(Long orderId);
	
	List<OrderDTO> getOrder();
	
	PageDTO<OrderDTO> getOrders(Map<String, String> params);
	OrderDTO getOrderbyOrderId(Long orderId);
}
