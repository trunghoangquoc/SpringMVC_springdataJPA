package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.mapper.CategoryMapper;
import com.laptrinhjavaweb.model.CategoryModel;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO {

	@Override
	public List<CategoryModel> findAll() {
		String sql = "SELECT * FROM category";
		return query(sql, new CategoryMapper());
	}

	@Override
	public CategoryModel findOne(long id) {
		String sql = "SELECT * FROM category WHERE id = ?";
		List<CategoryModel> category = query(sql, new CategoryMapper(), id);
		return category.isEmpty() ? null : category.get(0);
	}

    @Override
    public CategoryModel findOneByCode(String code) {
		String sql = "SELECT * FROM category WHERE code = ?";
		List<CategoryModel> category = query(sql, new CategoryMapper(), code);
		return category.isEmpty() ? null : category.get(0);
    }

}
//List<CategoryModel> results = new ArrayList<>();
//Connection connection = getConnection();
//String sql = "SELECT * FROM category";
//PreparedStatement statement = null;
//ResultSet resultSet = null;
//if (connection != null) {
//	try {
//		statement = connection.prepareStatement(sql);
//		resultSet = statement.executeQuery();
//		while (resultSet.next()) {
//			CategoryModel category = new CategoryModel();
//			category.setId(resultSet.getLong("id"));
//			category.setCode(resultSet.getString("code"));
//			category.setName(resultSet.getNString("name"));
//			results.add(category);
//
//		}
//
//		return results;
//	} catch (Exception e) {
//		return null;
//	} finally {
//		try {
//			if (connection != null) {
//				connection.close();
//
//			}
//			if (statement != null) {
//				statement.close();
//			}
//			if (resultSet != null) {
//				resultSet.close();
//			}
//		} catch (Exception e) {
//			return null;
//		}
//	}
//
//}
//return null;
//}
//}

