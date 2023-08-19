package com.shoppeClone.shoppeClone.api.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppeClone.shoppeClone.dto.product.ProductDTO;
import com.shoppeClone.shoppeClone.service.ProductService;



@RestController
@RequestMapping("admin/api/v1/products")
public class ProductV1Api {

	@Autowired
	private ProductService productService;

	@PostMapping
	public ProductDTO createProduct(@RequestBody ProductDTO dto) {
		return productService.createProduct(dto);
	}

}
