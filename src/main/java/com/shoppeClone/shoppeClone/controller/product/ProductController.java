package com.shoppeClone.shoppeClone.controller.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/products")
public class ProductController {

	@GetMapping("add")
	public String add() {
		return "admin/product/add-product";
	}
}
