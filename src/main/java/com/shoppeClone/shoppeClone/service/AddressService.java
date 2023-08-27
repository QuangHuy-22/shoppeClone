package com.shoppeClone.shoppeClone.service;

import java.util.List;
import java.util.Map;

import com.shoppeClone.shoppeClone.dto.PageDTO;
import com.shoppeClone.shoppeClone.dto.address.AddressDTO;
import com.shoppeClone.shoppeClone.dto.category.CategoryDTO;

public interface AddressService {
	
	AddressDTO createAddress(AddressDTO dto);
	List<AddressDTO> getAddress();
	AddressDTO updateAddress(Long addressId,AddressDTO addressDTO);
	void deleteAddress(Long AddressIid);
	PageDTO<AddressDTO> getPageAddress(Map<String, String> params);
	
}
