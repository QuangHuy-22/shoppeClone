package com.shoppeClone.shoppeClone.converter.orderProduct;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shoppeClone.shoppeClone.converter.product.ProductConverter;
import com.shoppeClone.shoppeClone.dto.orderProduct.OrderProductDTO;
import com.shoppeClone.shoppeClone.dto.product.ProductDTO;
import com.shoppeClone.shoppeClone.entity.OrderProductEntity;
import com.shoppeClone.shoppeClone.entity.ProductEntity;
import com.shoppeClone.shoppeClone.respository.orderProduct.OrderProductRepository;

@Component
public class OrderProductConverter {

	@Autowired
	private OrderProductRepository orderProductRepository;

	@Autowired
	private ProductConverter productConverter;
	
	
	public OrderProductEntity toEntity(OrderProductDTO orderProductDTO) {
        OrderProductEntity orderProductEntity = new OrderProductEntity();
        
        orderProductEntity.setQuatity(orderProductDTO.getQuatity());

        // Chuyển đổi từ ProductDTO thành ProductEntity
        ProductDTO productDTO = orderProductDTO.getProduct();
        ProductEntity productEntity = productConverter.toEntity(productDTO);
        orderProductEntity.setProduct(productEntity);

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
			orderProductDTO.setQuantity(orderProductEntity.getQuatity());

			orderProductDTOs.add(orderProductDTO);
		}
		return orderProductDTOs;
	}

	public List<OrderProductEntity> toEntityList(List<OrderProductDTO> orderProductDTOs) {
		List<OrderProductEntity> orderProductEntities = new ArrayList<>();
		for (OrderProductDTO orderProductDTO : orderProductDTOs) {
			OrderProductEntity orderProductEntity = new OrderProductEntity();

			// Lấy các thuộc tinh trừ Id của OrderProductDTO sang OrderProductEntity
			 orderProductEntity.setOrderProductId(orderProductDTO.getOrderProductId());
			orderProductEntity.setQuatity(orderProductDTO.getQuatity());
			
			ProductEntity productEntity = productConverter.toEntity(orderProductDTO.getProduct());
	        orderProductEntity.setProduct(productEntity);
			
			
			//Thêm vào Danh sách của OrderProductEntity
			orderProductEntities.add(orderProductEntity);
		}
		return orderProductEntities;
	}

}
