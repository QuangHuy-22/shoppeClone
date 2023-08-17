package com.shoppeClone.shoppeClone.service;

import java.util.List;

import com.shoppeClone.shoppeClone.dto.RoleDTO;

public interface RoleService {
	
	RoleDTO createRole(RoleDTO roleDto);

	List<RoleDTO> getAll();

	RoleDTO updateRole(Long roleId, RoleDTO roleDTO);

	void deleteById(Long roleId);
}
