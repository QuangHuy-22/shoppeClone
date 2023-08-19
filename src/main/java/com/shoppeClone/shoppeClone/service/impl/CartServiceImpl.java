package com.shoppeClone.shoppeClone.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppeClone.shoppeClone.converter.cart.CartConverter;
import com.shoppeClone.shoppeClone.dto.PageDTO;
import com.shoppeClone.shoppeClone.dto.cart.CartDTO;
import com.shoppeClone.shoppeClone.entity.CartEntity;
import com.shoppeClone.shoppeClone.exception.ValidateException;
import com.shoppeClone.shoppeClone.respository.cart.CartRepository;
import com.shoppeClone.shoppeClone.service.CartService;
import com.shoppeClone.shoppeClone.utils.AppStringUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import javassist.NotFoundException;

@Service
@Transactional
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartConverter cartConverter;

	@Autowired
	private EntityManager entityManager;


	@Override
	public CartDTO getCartById(Long cartId) {
		CartEntity cartEntity = cartRepository.findById(cartId)
	            .orElseThrow(() -> new ValidateException("Không tồn tại giỏ hàng"));

	    return cartConverter.toDTO(cartEntity);
	}

	@Override
	public CartDTO addToCart(CartDTO cartDTO) throws NotFoundException {
		CartEntity cartEntity = cartConverter.toEntity(cartDTO);
        cartEntity = cartRepository.save(cartEntity);
        return cartConverter.toDTO(cartEntity);
	}

	@Override
	public void deleteCart(Long cartId) {
		cartRepository
			.findById(cartId).orElseThrow(() -> new ValidateException("Cart not found"));
		cartRepository.deleteById(cartId);
	}

	@Override
	public PageDTO<CartDTO> getCarts(Map<String, String> params) {
		// HQL
		// http://localhost:8080/admin/api/v1/carts?page=1&limit=10
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
		StringBuilder selectQueryBuilder = new StringBuilder("SELECT c FROM CartEntity c");
		StringBuilder countQueryBuilder = new StringBuilder("SELECT COUNT(c.cartId) FROM CartEntity c");

		String name = params.get("name");
		if (AppStringUtils.hasText(name)) {
			selectQueryBuilder.append(" WHERE c.name LIKE :name");
			countQueryBuilder.append(" WHERE c.name LIKE :name");
		}

		TypedQuery<CartEntity> selectQuery = entityManager.createQuery(selectQueryBuilder.toString(),
				CartEntity.class);

		TypedQuery<Long> countQuery = entityManager.createQuery(countQueryBuilder.toString(), Long.class);
		Integer firstItems = (page - 1) * limit;

		if (AppStringUtils.hasText(name)) {
			selectQuery.setParameter("name", "%" + name + "%");
			countQuery.setParameter("name", "%" + name + "%");
		}

		selectQuery.setFirstResult(firstItems);
		selectQuery.setMaxResults(limit);

		List<CartEntity> cartEntities = selectQuery.getResultList();
		Long totalItems = countQuery.getSingleResult();

		// entity -> dto
		List<CartDTO> dtos = cartConverter.toDTOList(cartEntities);

		return new PageDTO<>(page, limit, totalItems, dtos);
	}


}
