package com.shoppeClone.shoppeClone.api.order;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppeClone.shoppeClone.dto.PageDTO;
import com.shoppeClone.shoppeClone.dto.order.CreateOrderDTO;
import com.shoppeClone.shoppeClone.dto.order.OrderDTO;

import com.shoppeClone.shoppeClone.service.OrderService;

@RestController
@RequestMapping("/admin/api/v1/order")
public class OrderV1Api {
	
	@Autowired
	private OrderService orderService;
	
	
	@PostMapping
	public OrderDTO createOrder(@RequestBody CreateOrderDTO dto) {
		 
		return orderService.createOrder(dto);
	}
	
	@PutMapping("{orderId}")
	public OrderDTO updateOrder(@PathVariable(value="orderId") Long orderId,@RequestBody CreateOrderDTO dto) {
		return orderService.UpdateOrder(orderId, dto);
	}

	@GetMapping({"{orderId}"})
	public OrderDTO getOrerbyOrderId(@PathVariable(value = "orderId") Long orderId){
		return orderService.getOrderbyOrderId(orderId);
	}
	@GetMapping
	public PageDTO<OrderDTO> getPageProduct(
			@RequestParam Map<String, String> params
			) 
	{
		
		return orderService.getOrders(params);
	}
	@DeleteMapping("{orderid}")
	public void deleteAddress(@PathVariable Long orderid) {
		
		orderService.deleteOrder(orderid);
	}
	
}
