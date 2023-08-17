package com.shoppeClone.shoppeClone.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shoppeClone.shoppeClone.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	
	@Query("SELECT u FROM UserEntity u WHERE u.username = :username")
	Optional<UserEntity> findUserByUserName(
			@Param(value = "username")String name
			);
}
