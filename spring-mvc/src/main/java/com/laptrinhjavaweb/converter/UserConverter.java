package com.laptrinhjavaweb.converter;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.UserEntity;

@Component
public class UserConverter {
	public UserDTO toDto(UserEntity entity) {
		UserDTO result = new UserDTO();
		result.setFullName(entity.getFullName());
		result.setPassWord(entity.getPassword());
		result.setUserName(entity.getUserName());
		result.setStatus(entity.getStatus());
		return result;
	}
	
	
	
	//converter qua entity mới xuống data.
	//thêm mới thì ko cần có id
	public UserEntity toEntity(UserDTO dto) {
		
		 UserEntity userEntity = new UserEntity();
		 userEntity.setFullName(dto.getFullName());
		 userEntity.setPassword(dto.getPassWord());
		 userEntity.setStatus(dto.getStatus());
		 userEntity.setUserName(dto.getUserName());
		 
		return userEntity;
	}
	
}
