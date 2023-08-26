package com.shoppeClone.shoppeClone.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppeClone.shoppeClone.converter.orderProduct.OrderProductConverter;
import com.shoppeClone.shoppeClone.converter.product.ProductConverter;
import com.shoppeClone.shoppeClone.dto.order.CreateOrderDTO;
import com.shoppeClone.shoppeClone.dto.orderProduct.OrderProductDTO;
import com.shoppeClone.shoppeClone.dto.product.ProductDTO;
import com.shoppeClone.shoppeClone.entity.OrderEntity;
import com.shoppeClone.shoppeClone.entity.OrderProductEntity;
import com.shoppeClone.shoppeClone.entity.ProductEntity;
import com.shoppeClone.shoppeClone.exception.ValidateException;
import com.shoppeClone.shoppeClone.respository.order.OrderRepository;
import com.shoppeClone.shoppeClone.respository.orderProduct.OrderProductRepository;
import com.shoppeClone.shoppeClone.respository.product.ProductRepository;
import com.shoppeClone.shoppeClone.service.OrderProductService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderProductServiceImpl implements OrderProductService {

	@Autowired
	private OrderProductRepository orderProductRepository;

	@Autowired
	private OrderProductConverter orderProductConverter;

	@Autowired
	private ProductConverter productConverter;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public List<OrderProductDTO> createOrderProduct(List<OrderProductDTO> dto) {

		List<OrderProductEntity> orderProductEntities = new ArrayList<>();

		for (OrderProductDTO orderProduct : dto) {
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
			orderProductEntities.add(orderProductEntity);
		}

		orderProductRepository.saveAll(orderProductEntities);

		List<OrderProductDTO> result = orderProductConverter.toDTOList(orderProductEntities);
		return result;
	}

}
