package com.shoppeClone.shoppeClone.service;

import com.shoppeClone.shoppeClone.dto.order.CreateOrderDTO;
import com.shoppeClone.shoppeClone.dto.order.OrderDTO;

public interface OrderService {

	OrderDTO createOrder(CreateOrderDTO createOrderDTO);
}
