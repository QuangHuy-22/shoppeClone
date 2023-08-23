package com.shoppeClone.shoppeClone.dto.product;

import java.util.List;

import com.shoppeClone.shoppeClone.dto.ImageDTO;
import com.shoppeClone.shoppeClone.dto.category.CategoryDTO;
import com.shoppeClone.shoppeClone.dto.supplier.SupplierDTO;

public class ProductDTO {
	private Long productId;
    private String name;
    private Double importPrice;
    private Double price;
    private Integer discountPercent;
    private String description;
    private CategoryDTO category;
    private SupplierDTO supplier;
    private List<ImageDTO> images;
	public Long getProductId() {
		return productId;
	}
	public String getName() {
		return name;
	}
	public Double getImportPrice() {
		return importPrice;
	}
	public Double getPrice() {
		return price;
	}
	public Integer getDiscountPercent() {
		return discountPercent;
	}
	public String getDescription() {
		return description;
	}
	public CategoryDTO getCategory() {
		return category;
	}
	public SupplierDTO getSupplier() {
		return supplier;
	}
	public List<ImageDTO> getImages() {
		return images;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setImportPrice(Double importPrice) {
		this.importPrice = importPrice;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public void setDiscountPercent(Integer discountPercent) {
		this.discountPercent = discountPercent;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setCategory(CategoryDTO category) {
		this.category = category;
	}
	public void setSupplier(SupplierDTO supplier) {
		this.supplier = supplier;
	}
	public void setImages(List<ImageDTO> images) {
		this.images = images;
	}

}
