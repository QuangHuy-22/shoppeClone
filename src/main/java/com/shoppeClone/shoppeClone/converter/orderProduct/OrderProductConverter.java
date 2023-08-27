package com.shoppeClone.shoppeClone.converter.orderProduct;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shoppeClone.shoppeClone.dto.orderProduct.OrderProductDTO;
import com.shoppeClone.shoppeClone.entity.OrderEntity;
import com.shoppeClone.shoppeClone.entity.OrderProductEntity;
import com.shoppeClone.shoppeClone.entity.ProductEntity;
import com.shoppeClone.shoppeClone.exception.ValidateException;
import com.shoppeClone.shoppeClone.respository.order.OrderRepostory;
import com.shoppeClone.shoppeClone.respository.orderProduct.OrderProductRepository;
import com.shoppeClone.shoppeClone.respository.product.ProductRepository;

@Component
public class OrderProductConverter {

	@Autowired
	private OrderProductRepository orderProductRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderRepostory orderRepository;
	
	
	public OrderProductDTO toDTO(OrderProductEntity orderProductEntity) {
		OrderProductDTO orderProductDTO = new OrderProductDTO();
		orderProductDTO.setOrderProductId(orderProductEntity.getOrderProductId());
		orderProductDTO.setQuantity(orderProductEntity.getQuatity());
		orderProductDTO.setProductId(orderProductEntity.getProduct().getProductId());
		orderProductDTO.setOrderId(orderProductEntity.getOrder().getOrderId());
		
		return orderProductDTO;
		
	}
	
	public OrderProductEntity toEntity(OrderProductDTO dto, OrderProductEntity entity) {
		
		entity.setQuatity(dto.getQuantity());
		Long productId = dto.getProductId();
		Long orderId = dto.getOrderId();
		
		if (productId == null) {
			throw new ValidateException("Không tìm thấy Id của product");
		}
		if (orderId == null) {
			throw new ValidateException("Không tìm thấy Id của Order");
		}
		
		// Lấy Id của product trong DB
		ProductEntity productEntity = productRepository
				.findById(productId)
				.orElseThrow(() -> new ValidateException("Không có Id này trong DB"));
		
		// Lấy Id của order trong DB
		OrderEntity orderEntity = orderRepository
				.findById(orderId)
				.orElseThrow(() -> new ValidateException("Không có Id này trong DB"));
		
		entity.setProduct(productEntity);
		entity.setOrder(orderEntity);
		orderProductRepository.save(entity);
		
		return entity;
		
	}
	
	public List<OrderProductDTO> toDTOList(List<OrderProductEntity> orderProductEntities) {
		List<OrderProductDTO> orderProductDTOs = new ArrayList<>();
		for (OrderProductEntity orderProductEntity : orderProductEntities) {
			OrderProductDTO orderProductDTO = new OrderProductDTO();

			// Lấy các thuộc tính từ orderProductEntity và đặt vào orderProductDTO
			orderProductDTO.setOrderProductId(orderProductEntity.getOrderProductId());
			
			// Chuyển đổi từ Entity sang DTO
			orderProductDTO.setProductId(orderProductEntity.getProduct().getProductId()); 
			orderProductDTO.setOrderId(orderProductEntity.getOrder().getOrderId());
			orderProductDTO.setQuantity(orderProductEntity.getQuatity());

			orderProductDTOs.add(orderProductDTO);
		}
		return orderProductDTOs;
	}
	
	public List<OrderProductEntity> toEntityList(List<OrderProductDTO> orderProductDTOs){
		List<OrderProductEntity> orderProductEntities = new ArrayList<>();

		for (OrderProductDTO orderProduct : orderProductDTOs) {
			Long productId = orderProduct.getProductId();
			Long orderId = orderProduct.getOrderId();
			if (productId == null) {
				throw new ValidateException("ProductId null");
			}
			if (orderId == null) {
				throw new ValidateException("OrderId null");
			}
			if (orderProduct.getQuantity() == null) {
				throw new ValidateException("Quantity null");
			}

			ProductEntity productEntity = productRepository.findById(productId)
					.orElseThrow(() -> new ValidateException("Product không tồn tại"));

			OrderEntity orderEntity = orderRepository.findById(orderId)
					.orElseThrow(() -> new ValidateException("Orrder khog ton tai"));

			// Gán productEntity vào orderProductDTO trước khi chuyển đổi
			
			OrderProductEntity orderProductEntity = new OrderProductEntity();
			orderProductEntity.setOrder(orderEntity);
			orderProductEntity.setProduct(productEntity);
			orderProductEntity.setQuatity(orderProduct.getQuantity());
			orderProductRepository.save(orderProductEntity);
			
			// Trước khi add thì nhớ save vào.
			orderProductEntities.add(orderProductEntity);
		}
		return orderProductEntities;
	}
	
	
	

}
