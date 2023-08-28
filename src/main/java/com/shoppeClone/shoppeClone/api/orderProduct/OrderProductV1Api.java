package com.shoppeClone.shoppeClone.api.orderProduct;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public List<OrderProductDTO> createOrderProduct(
			@RequestBody List<OrderProductDTO> dto
						) 
	{
	
		return orderProductService.createOrderProduct(dto);
		
	}
	
	@GetMapping
	public List<OrderProductDTO> getAllOrderProduct()
	{
		return orderProductService.getAllOrderProduct();
	}
	@GetMapping("{orderProductId}")
	public OrderProductDTO getByIdOProduct(
			@PathVariable Long orderProductId
			)
	{
		return orderProductService.getByIdOProduct(orderProductId);
	}
	
	
	@PutMapping("{orderProductId}")
	public OrderProductDTO updateOProduct(
			@RequestBody OrderProductDTO dto,
			@PathVariable(value = "orderProductId") Long Id
			)
	{
		return orderProductService.updateOrderProduct(dto, Id);
	}
	
	@DeleteMapping("{orderProductId}")
	public void deleteOProduct(
			@PathVariable(value = "orderProductId") Long Id
			)
	{
		orderProductService.deleteOrderProduct(Id);
	}
	
}
