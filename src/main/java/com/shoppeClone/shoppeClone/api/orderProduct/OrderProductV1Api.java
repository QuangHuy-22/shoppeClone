package com.shoppeClone.shoppeClone.api.orderProduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppeClone.shoppeClone.dto.orderProduct.OrderProductDTO;
import com.shoppeClone.shoppeClone.service.OrderProductService;

@RestController
@RequestMapping("admin/api/v1/oderProducts")
public class OrderProductV1Api {
	
	@Autowired
	private OrderProductService orderProductService;

	@PostMapping
	public OrderProductDTO createOrderProduct(@RequestBody OrderProductDTO dto) {
		
		return orderProductService.createOrderProduct(dto);
	}
}
