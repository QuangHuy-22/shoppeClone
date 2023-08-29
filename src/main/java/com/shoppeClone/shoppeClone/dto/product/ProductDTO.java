package com.shoppeClone.shoppeClone.dto.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shoppeClone.shoppeClone.dto.category.CategoryDTO;
import com.shoppeClone.shoppeClone.dto.images.ImageDTO;
import com.shoppeClone.shoppeClone.dto.supplier.SupplierDTO;

public class ProductDTO {

    private Long productId;
    private String name;
    private Double importPrice;
    private Double price;
    private Integer discountPercent;
    private String description;
//    private Long categoryId; // Thay vì trực tiếp là CategoryDTO
//    private Long supplierId;
    
    private List<ImageDTO> imageIds;
    
    
    
    public List<ImageDTO> getImageIds() {
		return imageIds;
	}

	public void setImageIds(List<ImageDTO> imageIds) {
		this.imageIds = imageIds;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
	private Date createdDate;
    

    public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	private CategoryDTO category; // Thêm trường kiểu CategoryDTO
    
    public CategoryDTO getCategory() {
		return category;
	}

	public void setCategory(CategoryDTO category) {
		this.category = category;
	}

	public SupplierDTO getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierDTO supplier) {
		this.supplier = supplier;
	}

	private SupplierDTO supplier; // Thêm trường kiểu SupplierDTO
    
    private List<ImageDTO> images; // Thêm danh sách hình ảnh!!
    
    public List<ImageDTO> getImages() {
		return images;
	}

	public void setImages(List<ImageDTO> images) {
		this.images = images;
	}
	

	

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getImportPrice() {
		return importPrice;
	}

	public void setImportPrice(Double importPrice) {
		this.importPrice = importPrice;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(Integer discountPercent) {
		this.discountPercent = discountPercent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public Long getCategoryId() {
//		return categoryId;
//	}
//
//	public void setCategoryId(Long categoryId) {
//		this.categoryId = categoryId;
//		
//	}
//
//	public Long getSupplierId() {
//		return supplierId;
//	}
//
//	public void setSupplierId(Long supplierId) {
//		this.supplierId = supplierId;
//	}

	
    
    

	
}
