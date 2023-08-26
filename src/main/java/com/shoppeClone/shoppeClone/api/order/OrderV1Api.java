package com.shoppeClone.shoppeClone.api.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shoppeClone.shoppeClone.dto.order.CreateOrderDTO;
import com.shoppeClone.shoppeClone.dto.order.OrderDTO;
import com.shoppeClone.shoppeClone.service.OrderService;

@RestController
@RequestMapping("admin/api/v1/orders")
public class OrderV1Api {

	
	@Autowired
	private OrderService orderService;
	
	@PostMapping
	public OrderDTO createOrder(
			@RequestBody CreateOrderDTO dto
			) 
	{
		return orderService.createOrder(dto);
	}
}
