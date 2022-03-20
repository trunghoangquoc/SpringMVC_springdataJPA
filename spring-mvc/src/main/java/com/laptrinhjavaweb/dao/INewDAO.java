package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.model.NewModel;

public interface INewDAO extends GenericDAO<NewModel> {
	List<NewModel> findAll();
	
	
//	List<NewModel> findAll(Integer offset,Integer limit, String sortName, String sortBy );
//  vì findAll có rất nhiều parameter nên cần viết 1 Pageble để tổng hợp
}
