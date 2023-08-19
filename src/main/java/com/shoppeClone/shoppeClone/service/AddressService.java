package com.shoppeClone.shoppeClone.service;

import com.shoppeClone.shoppeClone.dto.address.AddressDTO;

public interface AddressService {

	AddressDTO  createAddress(AddressDTO addressDTO);
	
	AddressDTO updateAddress(Long addressId, AddressDTO addressDTO);
}
