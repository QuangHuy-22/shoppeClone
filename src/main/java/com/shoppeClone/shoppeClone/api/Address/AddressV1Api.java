package com.shopeeClone.shopeeClone.api.Address;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopeeClone.shopeeClone.dto.PageDTO;
import com.shopeeClone.shopeeClone.dto.address.AddressDTO;
import com.shopeeClone.shopeeClone.dto.category.CategoryDTO;
import com.shopeeClone.shopeeClone.service.AddressService;

@RestController
@RequestMapping("admin/api/v1/address")
public class AddressV1Api {

	@Autowired
	private AddressService addressService;
	
	@PostMapping()
	public AddressDTO createAddress(@RequestBody AddressDTO dto) {
		
		return addressService.createAddress(dto);
	}
	@PutMapping("{addressId}")
	public AddressDTO updateAddress(@PathVariable(value = "addressId") Long addressId,@RequestBody AddressDTO dto) {
		return addressService.updateAddress(addressId, dto);
	}
//	@GetMapping()
//	public PageDTO<AddressDTO> getAddress(
//			@RequestParam Map<String , String> params) {
//		return addressService.getAddress(params);
//	}
	@GetMapping
	public List<AddressDTO> getaddress(){
		return addressService.getAddress();
	}
	
	@DeleteMapping("{addressId}")
	public void deleteCategoryById(
			@PathVariable(value = "addressId") Long categoryId) {
		addressService.deleteAddress(categoryId);
	}
}
