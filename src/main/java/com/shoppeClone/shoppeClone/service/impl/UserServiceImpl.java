package com.shoppeClone.shoppeClone.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppeClone.shoppeClone.converter.user.UserConverter;
import com.shoppeClone.shoppeClone.dto.PageDTO;
import com.shoppeClone.shoppeClone.dto.user.UserDTO;
import com.shoppeClone.shoppeClone.entity.CategoryEntity;
import com.shoppeClone.shoppeClone.entity.UserEntity;
import com.shoppeClone.shoppeClone.exception.ValidateException;
import com.shoppeClone.shoppeClone.respository.UserRepository;
import com.shoppeClone.shoppeClone.service.UserService;
import com.shoppeClone.shoppeClone.utils.AppStringUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserConverter userConverter;
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public UserDTO createUser(UserDTO dto) {
		//		validate dữ liệu 
		String username = dto.getUsername();
				if (!AppStringUtils.hasText(username)) {
					throw new ValidateException("Username không được để trống");
				}
		//Dto -> entity
		UserEntity newUserEntity = userConverter.toEntity(dto);
		userRepository.save(newUserEntity);
		//entity -> dto
		UserDTO resultDto = userConverter.toDTO(newUserEntity);
		return resultDto;
	}

	@Override
	public UserDTO updateUser(Long userId, UserDTO userDTO) {
		UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new ValidateException("User không tồn tại"));
		
		userConverter.toEntity(userEntity, userDTO);
		userRepository.save(userEntity);
		
		return userConverter.toDTO(userEntity);
	}

	@Override
	public PageDTO<UserDTO> getUser(Map<String, String> params) {
		// HQL
		// http://localhost:8080/admin/api/v1/user?page=1&limit=10
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
		StringBuilder selectQueryBuilder = new StringBuilder("SELECT c FROM UserEntity c");
		StringBuilder countQueryBuilder 
		= new StringBuilder("SELECT COUNT(c.userId) FROM UserEntity c");
		
		String name = params.get("username");
		if (AppStringUtils.hasText(name)) {
			selectQueryBuilder.append(" WHERE c.username LIKE :username" );
			countQueryBuilder.append(" WHERE c.username LIKE :username");
		}
		TypedQuery<UserEntity> selectQuery 
		= entityManager.createQuery(selectQueryBuilder.toString(), UserEntity.class);

		TypedQuery<Long> countQuery 
		= entityManager.createQuery(countQueryBuilder.toString(), Long.class);
		Integer firstItems = (page - 1) * limit;
		
		if (AppStringUtils.hasText(name)) {
			selectQuery.setParameter("name", "%" + name + "%");
			countQuery.setParameter("name", "%" + name + "%");
		}
		selectQuery.setFirstResult(firstItems);
		selectQuery.setMaxResults(limit);
		
		List<UserEntity> userEntities = selectQuery.getResultList();
		Long totalItems = countQuery.getSingleResult();
		
		// entity -> dto
		List<UserDTO> dtos = userConverter.toDTOList(userEntities);
		return new PageDTO<>(page, limit, totalItems, dtos);
	}
}
