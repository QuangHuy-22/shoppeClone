package com.shoppeClone.shoppeClone.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppeClone.shoppeClone.converter.orderProduct.OrderProductConverter;


import com.shoppeClone.shoppeClone.dto.orderProduct.OrderProductDTO;
import com.shoppeClone.shoppeClone.entity.OrderProductEntity;
import com.shoppeClone.shoppeClone.exception.ValidateException;
import com.shoppeClone.shoppeClone.respository.orderProduct.OrderProductRepository;
import com.shoppeClone.shoppeClone.service.OrderProductService;

import jakarta.persistence.criteria.Order;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderProductServiceImpl implements OrderProductService {

	@Autowired
	private OrderProductRepository orderProductRepository;

	@Autowired
	private OrderProductConverter orderProductConverter;

	

	@Override
	public List<OrderProductDTO> createOrderProduct(List<OrderProductDTO> dto) {

		List<OrderProductEntity> orderProductEntities = orderProductConverter.toEntityList(dto);

		orderProductRepository.saveAll(orderProductEntities);

		List<OrderProductDTO> result = orderProductConverter.toDTOList(orderProductEntities);
		
		return result;
	}


	@Override
	public List<OrderProductDTO> getAllOrderProduct() {
		
		List<OrderProductEntity> orderProductEntities = orderProductRepository.findAll();
		List<OrderProductDTO> dtoList = orderProductConverter.toDTOList(orderProductEntities);
		return dtoList;
	}
	
	@Override
	public OrderProductDTO updateOrderProduct(OrderProductDTO dto, Long orderProductId) {
		OrderProductEntity orderProductEntity = orderProductRepository
				.findById(orderProductId)
				.orElseThrow(() -> new ValidateException("Không tìm thấy Id trong Db"));
		
		orderProductConverter.toEntity(dto, orderProductEntity);
		orderProductRepository.save(orderProductEntity);
		
		return orderProductConverter.toDTO(orderProductEntity);
	}
	
	@Override
	public void deleteOrderProduct(Long orderProductId) {
		OrderProductEntity orderProductEntity = orderProductRepository
				.findById(orderProductId)
				.orElseThrow(() -> new ValidateException("Không tìm thấy Id này trong Db"));
		
		orderProductRepository.delete(orderProductEntity);
		
	}
	
	@Override
	public OrderProductDTO getByIdOProduct(Long orderProductId) {
		
		OrderProductEntity orderProductEntity = orderProductRepository
				.findById(orderProductId)
				.orElseThrow(() -> new ValidateException("Khong tim th id"));
		orderProductRepository.save(orderProductEntity);
		return orderProductConverter.toDTO(orderProductEntity);
	}
}
