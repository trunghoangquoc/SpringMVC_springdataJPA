package com.laptrinhjavaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhjavaweb.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	//tại sao chỉ có name mà ko get passwork?
	//passwork đã xử lý khi BYding data lên rồi
	
	UserEntity findOneByUserNameAndStatus(String name, int status);
}
