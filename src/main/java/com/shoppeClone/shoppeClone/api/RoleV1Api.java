package com.shoppeClone.shoppeClone.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppeClone.shoppeClone.dto.RoleDTO;
import com.shoppeClone.shoppeClone.service.RoleService;

@RestController
@RequestMapping("api/v1/roles")
public class RoleV1Api {
	
	@Autowired
	private RoleService roleService;
	
	@PostMapping
	public RoleDTO createRole(@RequestBody RoleDTO dto) {
		RoleDTO newDto = roleService.createRole(dto);
		return newDto;
	}
	
	@GetMapping
	public List<RoleDTO> getAll(){
		return roleService.getAll();
	}
	
	@PutMapping("{roleId}")
	public RoleDTO updateRole(@PathVariable Long roleId, @RequestBody RoleDTO roleDTO) {
		return roleService.updateRole(roleId, roleDTO);
	}
	@DeleteMapping("{roleId}")
	public void deleteById(
			@PathVariable(value = "roleId") Long roleId ) {
		
	}
}
