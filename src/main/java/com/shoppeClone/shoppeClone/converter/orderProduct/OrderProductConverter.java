package com.shoppeClone.shoppeClone.converter.orderProduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shoppeClone.shoppeClone.converter.product.ProductConverter;
import com.shoppeClone.shoppeClone.dto.orderProduct.OrderProductDTO;
import com.shoppeClone.shoppeClone.entity.OrderProductEntity;
import com.shoppeClone.shoppeClone.respository.orderProduct.OrderProductRepository;

@Component
public class OrderProductConverter {

	@Autowired
	private OrderProductRepository orderProductRepository;
	
	@Autowired
	private ProductConverter productConverter;
	

	public OrderProductDTO toDTO(OrderProductEntity orderProductEntity) 
	{
		OrderProductDTO orderProductDTO = new OrderProductDTO();
		orderProductDTO.setOrderProductId(orderProductEntity.getOrderProductId());
		orderProductDTO.setQuatity(orderProductEntity.getQuatity());
		
		// Set Product bằng converter
		orderProductDTO.setProduct(productConverter.toDto(orderProductEntity.getProduct()));
		
		return orderProductDTO;
	}
	
	public OrderProductEntity toEntity(OrderProductDTO orderProductDTO) {
		OrderProductEntity orderProductEntity = new OrderProductEntity();
		updateFormEntity(orderProductEntity, orderProductDTO);
		
		return orderProductEntity;
	}
	
	private void updateFormEntity(OrderProductEntity orderProductEntity,
			OrderProductDTO orderProductDTO) {
		orderProductEntity.setQuatity(orderProductDTO.getQuatity());
	}
	
}
