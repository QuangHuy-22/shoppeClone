package com.shoppeClone.shoppeClone.api.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppeClone.shoppeClone.dto.address.AddressDTO;
import com.shoppeClone.shoppeClone.service.AddressService;


@RestController
@RequestMapping("/admin/api/v1/address")
public class AddressApiV1 {

	@Autowired
	private AddressService addressService; 					
	
	@PostMapping
	public AddressDTO createAddress(
		@RequestBody AddressDTO  dto) {
		return addressService.createAddress(dto);
	}
	@PutMapping("{addressId}")
	public AddressDTO updateAddress(
			@PathVariable(value = "addressId") Long addressId,
			@RequestBody AddressDTO addressDTO) {
		return addressService.updateAddress(addressId, addressDTO);	
	}
	
	
}
