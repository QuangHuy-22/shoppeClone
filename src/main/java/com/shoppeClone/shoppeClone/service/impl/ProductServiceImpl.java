package com.shoppeClone.shoppeClone.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppeClone.shoppeClone.converter.product.ProductConverter;
import com.shoppeClone.shoppeClone.dto.PageDTO;
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

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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

	@Autowired
	private EntityManager entityManager;

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
		List<ProductDTO> productDTOs = productConverter.toDTOList(productEntities);
		return productDTOs;
	}

	@Override
	public PageDTO<ProductDTO> getPageProduct(Map<String, String> params) {

		String pagestr = params.get("page");
		String limitstr = params.get("limit");
		Integer page = 1;
		Integer limit = 10;

		if (AppStringUtils.hasText(pagestr)) {
			page = Integer.valueOf(pagestr);
		}

		if (AppStringUtils.hasText(limitstr)) {
			limit = Integer.valueOf(limitstr);
		}

		// Lấy dữ liệu
		StringBuilder selectQueryBuilder = new StringBuilder("select p from ProductEntity p ");
		StringBuilder countQueryBuilder = new StringBuilder("select count(p.productId) from ProductEntity p ");

		// khai báo url, description
		String name = params.get("name");
		String description = params.get("description");

		// Tìm kiếm theo url và description
		if (AppStringUtils.hasText(name)) {
			selectQueryBuilder.append("where p.name like :name");
			countQueryBuilder.append("where p.name like : name");
		}

		if (AppStringUtils.hasText(description)) {
			selectQueryBuilder.append("where p.description like :description");
			countQueryBuilder.append("where p.description like :description");
		}

		TypedQuery<ProductEntity> selectQuery = entityManager.createQuery(selectQueryBuilder.toString(),
				ProductEntity.class);

		TypedQuery<Long> countQuery = entityManager.createQuery(countQueryBuilder.toString(), Long.class);

		Integer firsImage = (page - 1) * limit;

		// Tìm kiếm tương đối
		if (AppStringUtils.hasText(name)) {
			selectQuery.setParameter("name", "%" + name + "%");
			countQuery.setParameter("name", "%" + name + "%");
		}

		if (AppStringUtils.hasText(description)) {
			selectQuery.setParameter("description", "%" + description + "%");
			countQuery.setParameter("description", "%" + description + "%");
		}

		selectQuery.setFirstResult(firsImage);
		selectQuery.setMaxResults(limit);

		List<ProductEntity> productEntities = selectQuery.getResultList();
		Long totalItems = countQuery.getSingleResult();

		//entity -> dto
		List<ProductDTO> productDTOs = productConverter.toDTOList(productEntities);

		return new PageDTO<>(page, limit, totalItems, productDTOs);
	}
	
	@Override
	public ProductDTO getByIdProduct(Long productId) {
		ProductEntity productEntity = productRepository
				.findById(productId)
				.orElseThrow(() -> new ValidateException("Không tìm thấy Id của product"));
				
		return productConverter.toDto(productEntity);
	}

}
