package com.shopeeClone.shopeeClone.dto.address;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shopeeClone.shopeeClone.entity.DistrictEntity;
import com.shopeeClone.shopeeClone.entity.ProvinceEntity;
import com.shopeeClone.shopeeClone.entity.WardEntity;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class AddressDTO {
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

