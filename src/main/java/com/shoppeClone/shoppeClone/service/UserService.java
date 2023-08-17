package com.shoppeClone.shoppeClone.service;

import java.util.Map;

import com.shoppeClone.shoppeClone.dto.PageDTO;
import com.shoppeClone.shoppeClone.dto.user.UserDTO;

public interface UserService {

	UserDTO createUser(UserDTO dto);

	UserDTO updateUser(Long userId, UserDTO dto);

	PageDTO<UserDTO> getUser(Map<String, String> params);
	
}
