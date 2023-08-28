package com.shoppeClone.shoppeClone.dto.address;

import com.shoppeClone.shoppeClone.dto.district.DistrictDTO;
import com.shoppeClone.shoppeClone.dto.province.ProvinceDTO;
import com.shoppeClone.shoppeClone.dto.ward.WardDTO;


public class AddressDTO {
	private Long addressId;
	
	private String description;

	private ProvinceDTO province;
	
	private DistrictDTO district;
	
	private WardDTO ward;

	

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



	public ProvinceDTO getProvince() {
		return province;
	}



	public void setProvince(ProvinceDTO province) {
		this.province = province;
	}



	public DistrictDTO getDistrict() {
		return district;
	}



	public void setDistrict(DistrictDTO district) {
		this.district = district;
	}



	public WardDTO getWard() {
		return ward;
	}



	public void setWard(WardDTO ward) {
		this.ward = ward;
	}



	@Override
	public String toString() {
		return "AddressDTO [addressId=" + addressId + ", description=" + description + ", province=" + province
				+ ", district=" + district + ", ward=" + ward + "]";
	}
	
}
