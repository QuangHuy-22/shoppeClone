package com.shoppeClone.shoppeClone.service;

import java.util.List;
import java.util.Map;

import com.shoppeClone.shoppeClone.dto.PageDTO;
import com.shoppeClone.shoppeClone.dto.category.CategoryDTO;

public interface CategoryService {
	
	CategoryDTO createCategory(CategoryDTO dto);
	
	PageDTO<CategoryDTO> getCategories( Map<String, String> params);
	
	CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO);
	
	void deleteCategory(Long categoryId);

	CategoryDTO getCategoryByCategoryId(long parseLong);

}
