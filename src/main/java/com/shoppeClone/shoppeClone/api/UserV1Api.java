package com.shoppeClone.shoppeClone.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppeClone.shoppeClone.dto.PageDTO;
import com.shoppeClone.shoppeClone.dto.user.UserDTO;
import com.shoppeClone.shoppeClone.service.UserService;

@RestController
@RequestMapping("api/v1/users")
public class UserV1Api {
	
	@Autowired
	private UserService userService;

	@PostMapping
	public UserDTO createUser(@RequestBody UserDTO dto)
	{
		return userService.createUser(dto);
	}
	
	@PutMapping("{userId}")
	public UserDTO updateUser(@PathVariable(value ="userId") Long userId, @RequestBody UserDTO dto) {
		return userService.updateUser(userId,dto);
	}
	
	@GetMapping
	public PageDTO<UserDTO> getUser(
			@RequestParam Map<String, String> params) {
		return userService.getUser(params);
	}
}
