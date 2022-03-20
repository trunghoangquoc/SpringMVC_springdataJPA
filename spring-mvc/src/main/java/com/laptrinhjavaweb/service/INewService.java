package com.laptrinhjavaweb.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.laptrinhjavaweb.dto.NewDTO;


public interface INewService {
	NewDTO findById (long id);
	
	List<NewDTO> findAll(Pageable pageable);
	int getTotalItem ();
	
	NewDTO save(NewDTO dto); // save = insert + update
	// 2 hàm thêm mới và update
	/*
	 * NewDTO insert (NewDTO newDto); 
	 * NewDTO update (NewDTO updateNew);
	 */
	void delete(long[] ids);

}
