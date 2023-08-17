package com.shoppeClone.shoppeClone.api.category;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppeClone.shoppeClone.dto.PageDTO;
import com.shoppeClone.shoppeClone.dto.category.CategoryDTO;
import com.shoppeClone.shoppeClone.service.CategoryService;

@RestController
@RequestMapping("admin/api/v1/categories")
public class CategoryV1Api {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	public CategoryDTO createCategory(
			@RequestBody CategoryDTO dto) {
		return categoryService.createCategory(dto);
	}
	
	@PutMapping("{categoryId}")
	public CategoryDTO updateCategory(@PathVariable(value = "categoryId") Long categoryId,
			@RequestBody CategoryDTO dto) {
		return categoryService.updateCategory(categoryId, dto);
	}
	
	@GetMapping
	public PageDTO<CategoryDTO> getCategories(
			@RequestParam Map<String, String> params
			) {
		return categoryService.getCategories(params);
	}
	
	@DeleteMapping("{categoryId}")
	public void deleteCategoryById(
			@PathVariable(value = "categoryId") Long categoryId) {
		categoryService.deleteCategory(categoryId);
	}
	

}
