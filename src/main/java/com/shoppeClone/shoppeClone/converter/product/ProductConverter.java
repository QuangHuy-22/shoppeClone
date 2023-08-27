package com.shoppeClone.shoppeClone.converter.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shoppeClone.shoppeClone.converter.category.CategoryConverter;
import com.shoppeClone.shoppeClone.converter.image.ImageConverter;
import com.shoppeClone.shoppeClone.converter.supplier.SupplierConverter;
import com.shoppeClone.shoppeClone.dto.product.CreateProductDTO;
import com.shoppeClone.shoppeClone.dto.product.ProductDTO;
import com.shoppeClone.shoppeClone.entity.CategoryEntity;
import com.shoppeClone.shoppeClone.entity.ImageEntity;
import com.shoppeClone.shoppeClone.entity.ProductEntity;
import com.shoppeClone.shoppeClone.entity.SupplierEntity;
import com.shoppeClone.shoppeClone.exception.ValidateException;
import com.shoppeClone.shoppeClone.respository.category.CategoryRepository;
import com.shoppeClone.shoppeClone.respository.image.ImageRepository;
import com.shoppeClone.shoppeClone.respository.supplier.SupplierRepository;



@Component
public class ProductConverter {
	
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private SupplierRepository supplierRepository; 
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private CategoryConverter categoryConverter;
	
	@Autowired
	private SupplierConverter supplierConverter;

	private ImageConverter imageConverter; 
		
	public ProductDTO toDto(ProductEntity productEntity) {

		 if (productEntity == null) {
	            return null;
	        }

	        ProductDTO productDTO = new ProductDTO();
	        productDTO.setProductId(productEntity.getProductId());
	        productDTO.setName(productEntity.getName());
	        productDTO.setImportPrice(productEntity.getImportPrice());
	        productDTO.setPrice(productEntity.getPrice());
	        productDTO.setDiscountPercent(productEntity.getDiscountPercent());
	        productDTO.setDescription(productEntity.getDescription());
	        productDTO.setCreatedDate(productEntity.getCreateDate());

	        // Assuming you have converters for CategoryEntity and SuppilierEntity
	        productDTO.setCategory(categoryConverter.toDTO(productEntity.getCategory()));
	        productDTO.setSupplier(supplierConverter.toDTO(productEntity.getSupplier()));

	        // Assuming you have a converter for ImageEntity
	        productDTO.setImages(imageConverter.toDTOList(productEntity.getImages()));
	       
		
		return productDTO;

	}
	
	public ProductEntity toEntity(CreateProductDTO dto) {
        ProductEntity productEntity = new ProductEntity();
        updateProductEntityFromDto(productEntity, dto);
        return productEntity;
    }

    public ProductEntity toEntity(ProductEntity productEntity, CreateProductDTO productDTO) {
        updateProductEntityFromDto(productEntity, productDTO);
        return productEntity;
    }

    private void updateProductEntityFromDto(ProductEntity productEntity, CreateProductDTO dto) {
        productEntity.setName(dto.getName());
        productEntity.setDescription(dto.getDescription());
        productEntity.setImportPrice(dto.getImportPrice());
        productEntity.setPrice(dto.getPrice());
        productEntity.setDiscountPercent(dto.getDiscountPercent());
        productEntity.setCreateDate(dto.getCreatedDate());

        CategoryEntity category = getCategoryById(dto.getCategoryId());
        productEntity.setCategory(category);

        SupplierEntity supplier = getSupplierById(dto.getSupplierId());
        productEntity.setSupplier(supplier);

        List<Long> imageIds = dto.getImageIds();
        List<ImageEntity> images = new ArrayList<>();
        for (Long imageId : imageIds) {
            ImageEntity image = getImageById(imageId);
            images.add(image);
        }
        productEntity.setImages(images);
    }
    
    public List<ProductDTO> toDTOList(List<ProductEntity> productEntites) {
    	List<ProductDTO> productDTOList = new ArrayList<>();
    	for (ProductEntity productEntity : productEntites) {
			productDTOList.add(toDto(productEntity));
		}
    	return productDTOList;
    }

    private CategoryEntity getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ValidateException("Không tìm thấy danh mục"));
    }

    private SupplierEntity getSupplierById(Long supplierId) {
        return supplierRepository.findById(supplierId)
                .orElseThrow(() -> new ValidateException("Không tìm thấy nhà cung cấp"));
    }

    private ImageEntity getImageById(Long imageId) {
        return imageRepository.findById(imageId)
                .orElseThrow(() -> new ValidateException("Không tìm thấy hình ảnh"));
    }
}


