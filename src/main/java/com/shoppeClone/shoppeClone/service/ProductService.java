package com.shoppeClone.shoppeClone.service;

import java.util.List;

import com.shoppeClone.shoppeClone.dto.product.CreateProductDTO;
import com.shoppeClone.shoppeClone.dto.product.ProductDTO;

public interface ProductService {

	ProductDTO createProduct(CreateProductDTO dto);
	
	ProductDTO updateProduct(Long productId, CreateProductDTO dto);
	
	void deleteProduct(Long productId);
	
	List<ProductDTO> getListProduct();
}
