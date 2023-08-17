package com.shoppeClone.shoppeClone.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.shoppeClone.shoppeClone.dto.RoleDTO;
import com.shoppeClone.shoppeClone.entity.RoleEntity;

@Component
public class RoleConverter {
//	dto--> entity
	public RoleEntity toEntity(RoleDTO dto) {
		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setCode(dto.getCode());
		roleEntity.setName(dto.getName());
		return roleEntity;
		
	}
	
//	Entity --> dto
	public RoleDTO toDto(RoleEntity entity) {
		RoleDTO dto = new RoleDTO();
		dto.setCode(entity.getCode());
		dto.setName(entity.getName());
		dto.setRoleId(entity.getRoleId());
		return dto;
		
	}
	
	public List<RoleDTO> toDto(List<RoleEntity> roleEntities) {
		List<RoleDTO> roleDtos = new ArrayList<>();
		for (RoleEntity entity : roleEntities) {
			roleDtos.add(toDto(entity));
		}
		return roleDtos;
	}
}