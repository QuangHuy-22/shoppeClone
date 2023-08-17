package com.shoppeClone.shoppeClone.controller.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppeClone.shoppeClone.dto.category.CategoryDTO;
import com.shoppeClone.shoppeClone.service.CategoryService;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("add")
	public String showAddCategoryPage() {
		return "admin/category/add-category";
	}
	
	@GetMapping("search")
	public String showSearchCategoriesPages() {
		return "admin/category/search-category";
	}
	@GetMapping("{categoryId}")
	public String showUpdateCategoryPage(@PathVariable String categoryId,
			Model model
			) {
		CategoryDTO categoryDTO = categoryService.getCategoryByCategoryId(Long.parseLong(categoryId));
		model.addAttribute("category", categoryDTO);
		return "admin/category/update-categories";
	}
	
}
