package com.laptrinhjavaweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IUserService;

@Service
public class UserService implements IUserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserConverter userConverter;
	
	
	@Override
	@Transactional
	public UserDTO save(UserDTO dto) {
		//khi thêm 1 account thì sẽ gửi vào thằng dto.
		//converter dto sang entity để lưu xuống databasse
		//sau đó converter lại sang dto để return 
		//(vì hàm trả về là kiểu dữ liệu UserDTO)
		
		 UserEntity userEntity = new UserEntity();
		 userEntity = userConverter.toEntity(dto);
		return userConverter.toDto(userRepository.save(userEntity));
	}
 
}
