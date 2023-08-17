package com.shoppeClone.shoppeClone.converter.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shoppeClone.shoppeClone.converter.RoleConverter;
import com.shoppeClone.shoppeClone.dto.RoleDTO;
import com.shoppeClone.shoppeClone.dto.user.UserDTO;
import com.shoppeClone.shoppeClone.entity.RoleEntity;
import com.shoppeClone.shoppeClone.entity.UserEntity;
import com.shoppeClone.shoppeClone.respository.RoleRepository;

@Component
public class UserConverter {
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private RoleConverter roleConverter;
	
	public UserDTO toDTO(UserEntity userEntity) {
		UserDTO userDTO= new UserDTO();
		userDTO.setUserId(userEntity.getUserId());
		userDTO.setAddress(userEntity.getAddress());
		userDTO.setUsername(userEntity.getUsername());
		userDTO.setFirstName(userEntity.getFirstName());
		userDTO.setLastName(userEntity.getLastName());
		userDTO.setCreateBy(userEntity.getCreateBy());
		userDTO.setCreateDate(userEntity.getCreateDate());
		userDTO.setModifierBy(userEntity.getModifierBy());
		userDTO.setModifierDate(userEntity.getModifierDate());
		userDTO.setPhoneNumber(userEntity.getPhoneNumber());
		userDTO.setRole(roleConverter.toDto(userEntity.getRoles()));
		return userDTO;
	}

	public UserEntity toEntity(UserDTO userDTO) {
		UserEntity userEntity = new UserEntity();
		userEntity.setAddress(userDTO.getAddress());
		userEntity.setFirstName(userDTO.getFirstName());
		userEntity.setLastName(userDTO.getLastName());
		userEntity.setPassword(userDTO.getPassword());
		userEntity.setUsername(userDTO.getUsername());
		userEntity.setPhoneNumber(userDTO.getPhoneNumber());
		for(RoleDTO roleDto: userDTO.getRole()) {
			Optional<RoleEntity> roleEntity = roleRepository.findByCode(roleDto.getCode());
			if (roleEntity.isPresent()) {
				userEntity.addRole(roleEntity.get());
			}
		}
		return userEntity;
		
	}
	
	public UserEntity toEntity(UserEntity userEntity, UserDTO userDTO) {
		userEntity.setAddress(userDTO.getAddress());
		userEntity.setFirstName(userDTO.getFirstName());
		userEntity.setLastName(userDTO.getLastName());
		userEntity.setPassword(userDTO.getPassword());
		userEntity.setUsername(userDTO.getUsername());
		userEntity.setPhoneNumber(userDTO.getPhoneNumber());
		List<RoleEntity> roles = new ArrayList<>();
		for(RoleDTO roleDto: userDTO.getRole()) {
			Optional<RoleEntity> roleEntity = roleRepository.findByCode(roleDto.getCode());
			if (roleEntity.isPresent()) {
				roles.add(roleEntity.get());
			}
		}
		
		return userEntity;
	}

	public List<UserDTO> toDTOList(List<UserEntity> userEntities) {
		List<UserDTO> userList = new ArrayList<>();
		for(UserEntity userEntity : userEntities) {
			userList.add(toDTO(userEntity));
		}
		return userList;
	}
}
