package com.shoppeClone.shoppeClone.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppeClone.shoppeClone.converter.order.OrderConverter;
import com.shoppeClone.shoppeClone.dto.order.CreateOrderDTO;
import com.shoppeClone.shoppeClone.dto.order.OrderDTO;
import com.shoppeClone.shoppeClone.dto.orderProduct.OrderProductDTO;
import com.shoppeClone.shoppeClone.entity.OrderEntity;
import com.shoppeClone.shoppeClone.entity.OrderProductEntity;
import com.shoppeClone.shoppeClone.exeption.ValidateException;
import com.shoppeClone.shoppeClone.repository.order.OrderRepostory;
import com.shoppeClone.shoppeClone.repository.orderProduct.OrderProductReposotory;
import com.shoppeClone.shoppeClone.service.OrderService;
import com.shoppeClone.shoppeClone.utils.AppStringUtils;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderConverter orderConverter;
	@Autowired
	private OrderRepostory orderRepostory;
	@Autowired
	private OrderProductReposotory orderProductReposotory;
	@Override
	public OrderDTO createOrder(CreateOrderDTO createOrderDTO) {
//		Long orderId = createOrderDTO.getOrderId();
//	    if (orderId == null) {
//	        throw new ValidateException("Order id cannot be empty");
//	    }

	    // Kiểm tra người dùng trước khi gọi getUserId()
	    if (createOrderDTO.getUserId() == null) {
	        throw new ValidateException("User cannot be null");
	    }
		OrderEntity orderEntity = orderConverter.toEntity(createOrderDTO);
		orderRepostory.save(orderEntity);
		OrderDTO  resultDto = orderConverter.toDTO(orderEntity);
		return resultDto;
	}

	@Override
	public OrderDTO UpdateOrder(Long orderId, CreateOrderDTO dto) {
		
		OrderEntity existingOrder = orderRepostory.findById(orderId)
	            .orElseThrow(() -> new ValidateException("Order not found with ID: " + orderId));

	        // Cập nhật thông tin từ updatedOrderDTO vào existingOrder
	        existingOrder = orderConverter.toEntity(existingOrder, dto);

	        // Lưu đơn hàng đã cập nhật vào cơ sở dữ liệu
	        OrderEntity updatedOrder = orderRepostory.save(existingOrder);

	        // Chuyển đổi thành DTO để trả về
	        return orderConverter.toDTO(updatedOrder);
	}

	@Override
	public void deleteOrder(Long orderId) {
		OrderEntity orderEntity = orderRepostory.findById(orderId).orElseThrow(()-> new ValidateException("Không tìm thấy order"));
		List<OrderProductEntity> orderProductEntities = new ArrayList<>();
		for(OrderProductEntity entity : orderProductEntities) {
			orderProductReposotory.delete(entity);
		}
		orderRepostory.delete(orderEntity);
	}

	@Override
	public List<OrderDTO> getOrder() {
		List<OrderEntity> orderEntities = orderRepostory.findAll();
		List<OrderDTO> dtos = orderConverter.toDTO(orderEntities);
		return dtos;
	}

	

}
