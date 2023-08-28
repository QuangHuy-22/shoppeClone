package com.shoppeClone.shoppeClone.dto.address;

public class CreateAddressDTO {
	private Long addressId;
	
	private String description;

	private Long province;
	
	private Long district;
	
	private Long ward;

	public Long getAddressId() {
		return addressId;
	}


	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}


	


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	


	public Long getProvince() {
		return province;
	}


	public void setProvince(Long province) {
		this.province = province;
	}


	public Long getDistrict() {
		return district;
	}


	public void setDistrict(Long districtEntity) {
		this.district = districtEntity;
	}


	public Long getWard() {
		return ward;
	}


	public void setWard(Long ward) {
		this.ward = ward;
	}



	

	

	

	
	
}

