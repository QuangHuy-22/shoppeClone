package com.shoppeClone.shoppeClone.converter.order;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shoppeClone.shoppeClone.converter.address.AddressConverter;
import com.shoppeClone.shoppeClone.converter.orderProduct.OrderProductConverter;
import com.shoppeClone.shoppeClone.converter.user.UserConverter;
import com.shoppeClone.shoppeClone.dto.order.CreateOrderDTO;
import com.shoppeClone.shoppeClone.dto.order.OrderDTO;
import com.shoppeClone.shoppeClone.dto.orderProduct.OrderProductDTO;
import com.shoppeClone.shoppeClone.entity.AddressEntity;
import com.shoppeClone.shoppeClone.entity.OrderEntity;
import com.shoppeClone.shoppeClone.entity.OrderProductEntity;
import com.shoppeClone.shoppeClone.entity.UserEntity;
import com.shoppeClone.shoppeClone.exeption.ValidateException;
import com.shoppeClone.shoppeClone.repository.UserRepository;
import com.shoppeClone.shoppeClone.repository.address.AddressRepository;
import com.shoppeClone.shoppeClone.repository.orderProduct.OrderProductReposotory;

@Component
public class OrderConverter {
	
	@Autowired
	private OrderProductConverter orderProductConverter;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private OrderProductReposotory orderProductReposotory;
	@Autowired
	private UserConverter userConverter;
	@Autowired 
	private AddressConverter addressConverter;
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

		List<OrderProductDTO> orderProductDTOs = orderProductConverter.toDTO(orderEntity.getOrderProducts());
		orderDTO.setOrderProductDTOs(orderProductDTOs);

		return orderDTO;
	}

	public List<OrderDTO> toDTO(List<OrderEntity> entities){
		
		List<OrderDTO> dtos = new ArrayList<>();
		for(OrderEntity entity : entities) {
			dtos.add(toDTO(entity));
		}
		
		return dtos;
	}
    
	public OrderEntity toEntity(CreateOrderDTO dto) {
    	
        OrderEntity entity = new OrderEntity();
        entity.setOrderId(dto.getOrderId());
        entity.setDescription(dto.getDescription());
        if (dto.getAddressId() != null) {
        AddressEntity addressEntity = addressRepository.findById(dto.getAddressId())
                .orElseThrow(() -> new ValidateException("Lỗi address"));
        entity.setAddress(addressEntity);
        }
        if (dto.getUserId() != null) {
            UserEntity userEntity = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new ValidateException("Lỗi user"));
            entity.setUser(userEntity);
        }
        List<Long> orderProductIds = dto.getOrderProductDTOs();
        
        for(Long orderProductId : orderProductIds) {
        	OrderProductEntity orderProductEntity = orderProductReposotory.findById(orderProductId).orElseThrow(() -> new ValidateException("Không tìm thấy orderProduct"));
        	entity.addOderProduct(orderProductEntity);
        }
        return entity;
    }
	public OrderEntity toEntity(OrderEntity entity,CreateOrderDTO createOrderDTO) {
		entity.setDescription(createOrderDTO.getDescription());
		  if (createOrderDTO.getAddressId() != null) {
		        AddressEntity addressEntity = addressRepository.findById(createOrderDTO.getAddressId())
		                .orElseThrow(() -> new ValidateException("Lỗi address"));
		        entity.setAddress(addressEntity);
		        }

		    if (createOrderDTO.getUserId() != null) {
		        UserEntity userEntity = userRepository.findById(createOrderDTO.getUserId())
		                .orElseThrow(() -> new ValidateException("Lỗi user"));
		        entity.setUser(userEntity);
		    }
        List<Long> orderProductIds = createOrderDTO.getOrderProductDTOs();
        
        for(Long orderProductId : orderProductIds) {
        	OrderProductEntity orderProductEntity = orderProductReposotory.findById(orderProductId).orElseThrow(() -> new ValidateException("Không tìm thấy orderProduct"));
        	entity.addOderProduct(orderProductEntity);
        }
		
		return entity;
	}
    
  
}
