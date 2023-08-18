package com.shopeeClone.shopeeClone.service;

import java.util.List;
import java.util.Map;

import com.shopeeClone.shopeeClone.dto.PageDTO;
import com.shopeeClone.shopeeClone.dto.address.AddressDTO;
import com.shopeeClone.shopeeClone.dto.category.CategoryDTO;

public interface AddressService {
	
	AddressDTO createAddress(AddressDTO dto);
	List<AddressDTO> getAddress();
	AddressDTO updateAddress(Long addressId,AddressDTO addressDTO);
	void deleteAddress(Long AddressIid);
	
}
