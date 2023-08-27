package com.shoppeClone.shoppeClone.service;

import java.util.List;
import java.util.Map;

import com.shoppeClone.shoppeClone.dto.PageDTO;
import com.shoppeClone.shoppeClone.dto.product.CreateProductDTO;
import com.shoppeClone.shoppeClone.dto.product.ProductDTO;

public interface ProductService {

	ProductDTO createProduct(CreateProductDTO dto);
	
	ProductDTO updateProduct(Long productId, CreateProductDTO dto);
	
	void deleteProduct(Long productId);
	
	List<ProductDTO> getListProduct();
	
	PageDTO<ProductDTO> getPageProduct(Map<String, String> params);
	
	ProductDTO getByIdProduct(Long productId);
}
