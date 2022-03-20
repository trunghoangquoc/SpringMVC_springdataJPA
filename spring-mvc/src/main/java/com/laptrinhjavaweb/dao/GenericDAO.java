package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.mapper.RowMapper;

public interface GenericDAO<T> {
	<T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters);
//	Viết hàm chung AbstractDAO, hàm chung( Ở lớp cha : GenericDAO) có 3 đối tượng truyền vào hàm: 
//query(
//	+SQL, 
//	+đội tượng cần trả về (ví dụ : đang làm vc vs newmodel thì trả về newmodel),
//	+giá trị của tham số)
//-> giá trị tham số có rất nhiều tham số nên tạo 1 cái đối tượng 
//		như 1 cái mảng Object...paramaters
	
	void update (String sql, Object... parameters);
	Long insert (String sql, Object... parameters);
	int count(String sql, Object... parameters);
}
