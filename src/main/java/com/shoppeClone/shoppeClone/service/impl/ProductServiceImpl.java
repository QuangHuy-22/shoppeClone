package com.shoppeClone.shoppeClone.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppeClone.shoppeClone.converter.product.ProductConverter;
import com.shoppeClone.shoppeClone.dto.product.ProductDTO;
import com.shoppeClone.shoppeClone.dto.supplier.SupplierDTO;
import com.shoppeClone.shoppeClone.entity.CategoryEntity;
import com.shoppeClone.shoppeClone.entity.ProductEntity;
import com.shoppeClone.shoppeClone.entity.SupplierEntity;
import com.shoppeClone.shoppeClone.exception.ValidateException;
import com.shoppeClone.shoppeClone.respository.category.CategoryRepository;
import com.shoppeClone.shoppeClone.respository.product.ProductRepository;
import com.shoppeClone.shoppeClone.respository.supplier.SupplierRepository;
import com.shoppeClone.shoppeClone.service.ProductService;
import com.shoppeClone.shoppeClone.utils.AppStringUtils;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductConverter productConverter;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	
	
	
	@Override
	public ProductDTO createProduct(ProductDTO dto) {
		// TODO Auto-generated method stub
		String name = dto.getName();
		if (!AppStringUtils.hasText(name)) {
			throw new ValidateException("Name không được để trống");
		}
		Long categoryId = dto.getCategoryId();
		CategoryEntity categoryEntity = categoryRepository.findById(categoryId)
		.orElseThrow(() -> new ValidateException("Khong tim thay id"));
		
		Long supplierId = dto.getSupplierId();
		SupplierEntity supplierEntity = supplierRepository.findById(supplierId)
		.orElseThrow(() -> new ValidateException("ko tim thay Id"));
		
		
		
		// Entity -> Dto
		
		ProductEntity productEntity = productConverter.toEntity(dto);
		productEntity.setCategory(categoryEntity);
		productEntity.setSupplier(supplierEntity);
		productRepository.save(productEntity);
		
		// Từ Dto -> Entity
		ProductDTO resultDto = productConverter.toDto(productEntity);
		return resultDto;
	}

}
