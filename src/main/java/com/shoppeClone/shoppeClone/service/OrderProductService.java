package com.shoppeClone.shoppeClone.service;

import java.util.List;

import com.shoppeClone.shoppeClone.dto.orderProduct.OrderProductDTO;


public interface OrderProductService {

	List<OrderProductDTO> createOrderProduct(List<OrderProductDTO> dto);
}
