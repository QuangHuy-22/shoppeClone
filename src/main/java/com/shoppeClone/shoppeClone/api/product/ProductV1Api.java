package com.shoppeClone.shoppeClone.api.product;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.shoppeClone.shoppeClone.dto.PageDTO;
import com.shoppeClone.shoppeClone.dto.product.CreateProductDTO;
import com.shoppeClone.shoppeClone.dto.product.ProductDTO;
import com.shoppeClone.shoppeClone.service.ProductService;


@RestController
@RequestMapping("admin/api/v1/products")
public class ProductV1Api {

	@Autowired
	private ProductService productService;

	@PostMapping
	public ProductDTO createProduct(
			@RequestBody CreateProductDTO dto
			) 
	{
		return productService.createProduct(dto);
	}
	@GetMapping
	public PageDTO<ProductDTO> getPageProduct(
			@RequestParam Map<String, String> params
			) 
	{
		return productService.getPageProduct(params);
	}
	
	@GetMapping("{productId}")
	public ProductDTO getByIdProduct(
			@PathVariable(value = "productId") Long productId
			)
	{
		return productService.getByIdProduct(productId);
	}
	
	@PutMapping("{productId}")
	public ProductDTO updateProduct(
			@PathVariable Long productId,
			@RequestBody CreateProductDTO dto
			) 
	{
		return productService.updateProduct(productId, dto);
	}
	
	@DeleteMapping("{productId}")
	public void deleteProduct(
			@PathVariable Long productId
			) 
	{
		productService.deleteProduct(productId);		
	}
}
