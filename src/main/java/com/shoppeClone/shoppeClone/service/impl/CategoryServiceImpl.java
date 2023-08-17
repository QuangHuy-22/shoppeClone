package com.shoppeClone.shoppeClone.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppeClone.shoppeClone.converter.category.CategoryConverter;
import com.shoppeClone.shoppeClone.dto.PageDTO;
import com.shoppeClone.shoppeClone.dto.category.CategoryDTO;
import com.shoppeClone.shoppeClone.entity.CategoryEntity;
import com.shoppeClone.shoppeClone.exception.ValidateException;
import com.shoppeClone.shoppeClone.respository.category.CategoryRepository;
import com.shoppeClone.shoppeClone.service.CategoryService;
import com.shoppeClone.shoppeClone.utils.AppStringUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryConverter categoryConverter;

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public CategoryDTO createCategory(CategoryDTO dto) {
		// Validate dữ liệu
		String name = dto.getName();
		if (!AppStringUtils.hasText(name)) {
			throw new ValidateException("Category name can not empty");
		}
		// Dto -> entity
		CategoryEntity newCategoryEntity = categoryConverter.toEntity(dto);
		categoryRepository.save(newCategoryEntity);
		// entity -> dto
		CategoryDTO resultDto = categoryConverter.toDTO(newCategoryEntity);
		return resultDto;
	}

	@Override
	public PageDTO<CategoryDTO> getCategories(Map<String, String> params) {
		// HQL
		// http://localhost:8080/admin/api/v1/categories?page=1&limit=10
		System.out.println(params);
		String pageStr = params.get("page");
		String limitStr = params.get("limit");
		Integer page = 1;
		Integer limit = 10;
		if (AppStringUtils.hasText(pageStr)) {
			page = Integer.valueOf(pageStr);
		}
		if (AppStringUtils.hasText(limitStr)) {
			limit = Integer.valueOf(limitStr);
		}
		// lấy dữ liệu
		// đếm dữ liệu
		StringBuilder selectQueryBuilder = new StringBuilder("SELECT c FROM CategoryEntity c");
		StringBuilder countQueryBuilder 
				= new StringBuilder("SELECT COUNT(c.categoryId) FROM CategoryEntity c");
		
		String name = params.get("name");
		if (AppStringUtils.hasText(name)) {
			selectQueryBuilder.append(" WHERE c.name LIKE :name" );
			countQueryBuilder.append(" WHERE c.name LIKE :name");
		}
	
		TypedQuery<CategoryEntity> selectQuery 
				= entityManager.createQuery(selectQueryBuilder.toString(), CategoryEntity.class);
		
		TypedQuery<Long> countQuery 
			= entityManager.createQuery(countQueryBuilder.toString(), Long.class);
		Integer firstItems = (page - 1) * limit;
		
		if (AppStringUtils.hasText(name)) {
			selectQuery.setParameter("name", "%" + name + "%");
			countQuery.setParameter("name", "%" + name + "%");
		}
		
		selectQuery.setFirstResult(firstItems);
		
		List<CategoryEntity> categoryEntities = selectQuery.getResultList();
		Long totalItems = countQuery.getSingleResult();
		
		// entity -> dto
		List<CategoryDTO> dtos = categoryConverter.toDTOList(categoryEntities);
		
		return new PageDTO<>(page, limit, totalItems, dtos);
	}
	
	@Override
	public CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO) {
		CategoryEntity categoryEntity = categoryRepository
				.findById(categoryId).orElseThrow(() -> new ValidateException("Category not found"));
		
		categoryConverter.toEntity(categoryEntity, categoryDTO);
		categoryRepository.save(categoryEntity);
		
		return categoryConverter.toDTO(categoryEntity);
	}

	@Override
	public void deleteCategory(Long categoryId) {
		categoryRepository
			.findById(categoryId).orElseThrow(() -> new ValidateException("Category not found"));
		categoryRepository.deleteById(categoryId);
	}
	
	@Override
	public CategoryDTO getCategoryByCategoryId(long categoryId) {
		CategoryEntity categoryEntity =
		categoryRepository.findById(categoryId).orElseThrow(() -> new ValidateException("Không tìm thấy danh mục"));
		return categoryConverter.toDTO(categoryEntity);
	}

}
