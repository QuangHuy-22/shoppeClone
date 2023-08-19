package com.shoppeClone.shoppeClone.converter.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shoppeClone.shoppeClone.converter.category.CategoryConverter;
import com.shoppeClone.shoppeClone.converter.image.ImageConverter;
import com.shoppeClone.shoppeClone.converter.supplier.SupplierConverter;
import com.shoppeClone.shoppeClone.dto.product.ProductDTO;
import com.shoppeClone.shoppeClone.entity.ProductEntity;
import com.shoppeClone.shoppeClone.respository.category.CategoryRepository;



@Component
public class ProductConverter {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	
	public ProductDTO toDto(ProductEntity productEntity) {

		ProductDTO productDTO = new ProductDTO();
		productDTO.setName(productEntity.getName());
		productDTO.setPrice(productEntity.getPrice());
		productDTO.setProductId(productEntity.getProductId());
		productDTO.setDiscountPercent(productEntity.getDiscountPercent());
		productDTO.setDescription(productEntity.getDescription());
		productDTO.setImportPrice(productEntity.getImportPrice());
		
		
		productDTO.setCategoryId(productEntity.getCategory().getCategoryId());
		productDTO.setSupplierId(productEntity.getSupplier().getSupplierId());
		
		
		// Set Category bằng categoryConverter
		
		
		// Set Category bằng  supplierConverter
		
		
		// Set Image bằng imageConverter
		
		
		return productDTO;

	}
	
	public ProductEntity toEntity(ProductDTO productDTO) {
		ProductEntity productEntity = new ProductEntity();
		productEntity.setName(productDTO.getName());
		productEntity.setPrice(productDTO.getPrice());
		productEntity.setProductId(productDTO.getProductId());
		productEntity.setDiscountPercent(productDTO.getDiscountPercent());
		productEntity.setDescription(productDTO.getDescription());
		productEntity.setImportPrice(productDTO.getImportPrice());
		
		
		// Set Category bằng categoryConverter, supplierConverter
		
		
		// Set Category bằng  supplierConverter
	
		
		// Set Image bằng imageConverter
		
		
		return productEntity;
	}
	
	public ProductEntity toEntity(ProductEntity productEntity, ProductDTO productDTO) {
		ProductEntity productEntities = new ProductEntity();
		productEntity.setName(productDTO.getName());
		productEntity.setPrice(productDTO.getPrice());
		productEntity.setProductId(productDTO.getProductId());
		productEntity.setDiscountPercent(productDTO.getDiscountPercent());
		productEntity.setDescription(productDTO.getDescription());
		productEntity.setImportPrice(productDTO.getImportPrice());
		
		// Set Category bằng categoryConverter
		
		
		// Set Category bằng  supplierConverter
		
		
		// Set Image bằng imageConverter
		
		
		return productEntities;
	}

}
