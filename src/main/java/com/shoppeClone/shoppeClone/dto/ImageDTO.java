package com.shoppeClone.shoppeClone.dto;

public class ImageDTO {
	private Long  imageId;
	
	private String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	private String description;

	public Long getImageId() {
		return imageId;
	}

	public String getDescription() {
		return description;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
