package com.shoppeClone.shoppeClone.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shoppeClone.shoppeClone.entity.RoleEntity;
import com.shoppeClone.shoppeClone.entity.UserEntity;
import com.shoppeClone.shoppeClone.exception.ApplicationException;
import com.shoppeClone.shoppeClone.respository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MyAppUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> userOpt = userRepository.findUserByUserName(username);
		if (userOpt.isEmpty()) {
			throw new ApplicationException("User not found");
		}
		UserEntity userEntity = userOpt.get();
		
		// User entity -> userDetail của Spring
		List<GrantedAuthority> roles = new ArrayList<>();
		
		List<RoleEntity> roleEntites = userEntity.getRoles();
		System.out.println(roleEntites);
		for (RoleEntity roleEntity : roleEntites) {
			GrantedAuthority authority = new SimpleGrantedAuthority(roleEntity.getCode());
			roles.add(authority);
		}
		
		UserDetails userDetail = User
				.withUsername(userEntity.getUsername())
				.password(userEntity.getPassword())
				.authorities(roles)
				.build();
		
		return userDetail;
	}
	
}
