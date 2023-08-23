package com.shoppeClone.shoppeClone.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppeClone.shoppeClone.converter.product.ProductConverter;
import com.shoppeClone.shoppeClone.dto.images.ImageDTO;
import com.shoppeClone.shoppeClone.dto.product.CreateProductDTO;
import com.shoppeClone.shoppeClone.dto.product.ProductDTO;
import com.shoppeClone.shoppeClone.dto.supplier.SupplierDTO;
import com.shoppeClone.shoppeClone.entity.CategoryEntity;
import com.shoppeClone.shoppeClone.entity.ImageEntity;
import com.shoppeClone.shoppeClone.entity.ProductEntity;
import com.shoppeClone.shoppeClone.entity.SupplierEntity;
import com.shoppeClone.shoppeClone.exception.ValidateException;
import com.shoppeClone.shoppeClone.respository.category.CategoryRepository;
import com.shoppeClone.shoppeClone.respository.image.ImageRepository;
import com.shoppeClone.shoppeClone.respository.product.ProductRepository;
import com.shoppeClone.shoppeClone.respository.supplier.SupplierRepository;
import com.shoppeClone.shoppeClone.service.ProductService;
import com.shoppeClone.shoppeClone.utils.AppStringUtils;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductConverter productConverter;
	
	@Autowired
	private ImageRepository imageRepository;

	@Override
	public ProductDTO createProduct(CreateProductDTO dto) {
		String name = dto.getName();
		validateProductName(name);

		ProductEntity productEntity = productConverter.toEntity(dto);
		productRepository.save(productEntity);

		ProductDTO resultDto = productConverter.toDto(productEntity);
		return resultDto;
	}

	@Override
	public ProductDTO updateProduct(Long productId, CreateProductDTO dto) {
		
		ProductEntity productEntity = getProductById(productId);
		productConverter.toEntity(productEntity, dto);
		productRepository.save(productEntity);

		return productConverter.toDto(productEntity);
	}

	

	@Override
	public void deleteProduct(Long productId) {
			// Kiểm tra sản phẩm xem có tồn tại hay không
			ProductEntity productEntity = getProductById(productId);
			
			
			// Xóa bản ghi liên quan trong image
			List<ImageEntity> images = new ArrayList<>();
			for (ImageEntity imageEntity : images) {
				imageRepository.delete(imageEntity);
			}
			
			productRepository.delete(productEntity);
		
		
	}
	
	private ProductEntity getProductById(Long productId) {
		return productRepository.findById(productId)
				.orElseThrow(() -> new ValidateException("Khong tim thấy sản phẩm để sửa"));

	}
	private void validateProductName(String name) {
		 if (!AppStringUtils.hasText(name)) {
			throw new ValidateException("Name không được để trống và phải khác null");
		}
	}

	@Override
	public List<ProductDTO> getListProduct() {
		List<ProductEntity> productEntities = productRepository.findAll();
		List<ProductDTO> productDTOs  = productConverter.toDTOList(productEntities);
		return productDTOs;
	}

}
