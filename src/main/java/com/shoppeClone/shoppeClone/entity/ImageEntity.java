package com.shoppeClone.shoppeClone.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="images")
public class ImageEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long imageId;
	
	private String url;
	
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private ProductEntity product;

	public Long getImageId() {
		return imageId;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
