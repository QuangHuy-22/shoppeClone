package com.shoppeClone.shoppeClone.service;

import java.util.List;

import com.shoppeClone.shoppeClone.dto.orderProduct.OrderProductDTO;


public interface OrderProductService {

	List<OrderProductDTO> createOrderProduct(List<OrderProductDTO> dto);
	
	List<OrderProductDTO> getAllOrderProduct();
	
	OrderProductDTO updateOrderProduct(OrderProductDTO dto, Long orderProductId);
	
	void deleteOrderProduct(Long orderProductId);
}
