package com.shoppeClone.shoppeClone.service;

import java.util.List;
import java.util.Map;

import com.shoppeClone.shoppeClone.dto.PageDTO;
import com.shoppeClone.shoppeClone.dto.address.AddressDTO;
import com.shoppeClone.shoppeClone.dto.address.CreateAddressDTO;


public interface AddressService {
	
	AddressDTO createAddress(CreateAddressDTO dto);
	List<CreateAddressDTO> getAddress();
	AddressDTO updateAddress(Long addressId,CreateAddressDTO addressDTO);
	void deleteAddress(Long AddressIid);
	PageDTO<AddressDTO> getOrders(Map<String, String> params);
	AddressDTO getOrderbyOrderId(Long addressId);
}
