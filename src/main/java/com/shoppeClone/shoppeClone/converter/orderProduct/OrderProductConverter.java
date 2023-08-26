package com.shoppeClone.shoppeClone.converter.orderProduct;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shoppeClone.shoppeClone.converter.order.OrderConverter;
import com.shoppeClone.shoppeClone.converter.product.ProductConverter;
import com.shoppeClone.shoppeClone.dto.orderProduct.OrderProductDTO;
import com.shoppeClone.shoppeClone.dto.product.ProductDTO;
import com.shoppeClone.shoppeClone.entity.OrderEntity;
import com.shoppeClone.shoppeClone.entity.OrderProductEntity;
import com.shoppeClone.shoppeClone.entity.ProductEntity;
import com.shoppeClone.shoppeClone.exception.ValidateException;
import com.shoppeClone.shoppeClone.respository.order.OrderRepository;
import com.shoppeClone.shoppeClone.respository.orderProduct.OrderProductRepository;
import com.shoppeClone.shoppeClone.respository.product.ProductRepository;

@Component
public class OrderProductConverter {

	@Autowired
	private OrderProductRepository orderProductRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderRepository orderRepository;
	
	
	public OrderProductEntity toEntity(OrderProductDTO orderProductDTO) {
        OrderProductEntity orderProductEntity = new OrderProductEntity();
        
        orderProductEntity.setQuatity(orderProductDTO.getQuantity());

        // Chuyển đổi từ ProductDTO thành ProductEntity
        Long productId = orderProductDTO.getProductId();
//        ProductEntity productEntity = productConverter.toEntity(productId);
//        orderProductEntity.setProduct(productEntity);

        return orderProductEntity;
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
			orderProductEntities.add(orderProductEntity);
		}
		return orderProductEntities;
	}

	

}
