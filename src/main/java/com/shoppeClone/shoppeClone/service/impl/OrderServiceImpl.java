package com.shoppeClone.shoppeClone.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.shoppeClone.shoppeClone.exception.ValidateException;
import com.shoppeClone.shoppeClone.respository.order.OrderRepostory;
import com.shoppeClone.shoppeClone.respository.orderProduct.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppeClone.shoppeClone.converter.order.OrderConverter;
import com.shoppeClone.shoppeClone.dto.PageDTO;
import com.shoppeClone.shoppeClone.dto.category.CategoryDTO;
import com.shoppeClone.shoppeClone.dto.order.CreateOrderDTO;
import com.shoppeClone.shoppeClone.dto.order.OrderDTO;
import com.shoppeClone.shoppeClone.dto.orderProduct.OrderProductDTO;
import com.shoppeClone.shoppeClone.entity.CategoryEntity;
import com.shoppeClone.shoppeClone.entity.OrderEntity;
import com.shoppeClone.shoppeClone.entity.OrderProductEntity;

import com.shoppeClone.shoppeClone.service.OrderService;
import com.shoppeClone.shoppeClone.utils.AppStringUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderConverter orderConverter;
	@Autowired
	private OrderRepostory orderRepostory;
	@Autowired
	private OrderProductRepository orderProductReposotory;

	@Autowired
	private EntityManager entityManager;
	
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

	@Override
	public PageDTO<OrderDTO> getOrders(Map<String, String> params) {
		System.out.println(params);
		String pageStr = params.get("page");
		String limitStr = params.get("limit");
		Integer page = 1;
		Integer limit = 10;
		if (AppStringUtils.hasText(pageStr)) {
			page = Integer.valueOf(pageStr);
		}
		if (AppStringUtils.hasText(limitStr)) {
			limit = Integer.valueOf(limitStr);
		}
		// lấy dữ liệu
		// đếm dữ liệu
		StringBuilder selectQueryBuilder = new StringBuilder("SELECT c FROM OrderEntity c");
		StringBuilder countQueryBuilder 
				= new StringBuilder("SELECT COUNT(c.orderId) FROM OrderEntity c");
		
		String name = params.get("orderId");
		if (AppStringUtils.hasText(name)) {
			selectQueryBuilder.append(" WHERE c.orderId LIKE :orderId" );
			countQueryBuilder.append(" WHERE c.orderId LIKE :orderId");
		}
	
		TypedQuery<OrderEntity> selectQuery 
				= entityManager.createQuery(selectQueryBuilder.toString(), OrderEntity.class);
		
		TypedQuery<Long> countQuery 
			= entityManager.createQuery(countQueryBuilder.toString(), Long.class);
		Integer firstItems = (page - 1) * limit;
		
		if (AppStringUtils.hasText(name)) {
			selectQuery.setParameter("orderId", "%" + name + "%");
			countQuery.setParameter("orderId", "%" + name + "%");
		}
		
		selectQuery.setFirstResult(firstItems);
		selectQuery.setMaxResults(limit);
		
		List<OrderEntity> orderEntities = selectQuery.getResultList();
		Long totalItems = countQuery.getSingleResult();
		
		// entity -> dto
		List<OrderDTO> dtos = orderConverter.toDTO(orderEntities);
		
		
		return new PageDTO<>(page, limit, totalItems, dtos);
	}

	@Override
	public OrderDTO getOrderbyOrderId(Long orderId) {
		 OrderEntity orElseThrow = orderRepostory.findById(orderId)
				.orElseThrow(() -> new ValidateException("Khong tim thấy order"));
		 return orderConverter.toDTO(orElseThrow);
		 
	}

	
	

	

}
