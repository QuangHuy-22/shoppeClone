package com.shoppeClone.shoppeClone.converter.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shoppeClone.shoppeClone.converter.orderProduct.OrderProductConverter;
import com.shoppeClone.shoppeClone.dto.order.CreateOrderDTO;
import com.shoppeClone.shoppeClone.dto.order.OrderDTO;
import com.shoppeClone.shoppeClone.dto.orderProduct.OrderProductDTO;
import com.shoppeClone.shoppeClone.entity.AddressEntity;
import com.shoppeClone.shoppeClone.entity.OrderEntity;
import com.shoppeClone.shoppeClone.entity.OrderProductEntity;
import com.shoppeClone.shoppeClone.entity.UserEntity;

@Component
public class OrderConverter {

	@Autowired
	private OrderProductConverter orderProductConverter;

	public OrderDTO toDTO(OrderEntity orderEntity) {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setOrderId(orderEntity.getOrderId());

		UserEntity user = orderEntity.getUser();
		if (user != null) {
			orderDTO.setUserId(user.getUserId());
		}
		AddressEntity address = orderEntity.getAddress();
		if (address != null) {
			orderDTO.setAddressId(address.getAddressId());
		}
		orderDTO.setDescription(orderEntity.getDescription());

		List<OrderProductDTO> orderProductDTOs = orderProductConverter.toDTOList(orderEntity.getOrderProduct());
		orderDTO.setOrderProducts(orderProductDTOs);

		return orderDTO;
	}

	public OrderEntity toEntity(CreateOrderDTO createOrderDTO) {
		OrderEntity orderEntity = new OrderEntity();

		// Set other properties like user, address, description
		UserEntity userEntity = new UserEntity();
		userEntity.setUserId(createOrderDTO.getUserId()); // Set the user's ID from the DTO
		orderEntity.setUser(userEntity);

		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setAddressId(createOrderDTO.getAddressId()); // Set the address's ID from the DTO
		orderEntity.setAddress(addressEntity);

		orderEntity.setDescription(createOrderDTO.getDescription()); // Set the description from the DTO

		List<OrderProductEntity> orderProductEntities = orderProductConverter
				.toEntityList(createOrderDTO.getOrderProducts());
		orderEntity.setOrderProduct(orderProductEntities);

		return orderEntity;
	}

}
