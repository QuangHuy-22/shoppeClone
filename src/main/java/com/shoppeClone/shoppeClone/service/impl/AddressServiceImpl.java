package com.shoppeClone.shoppeClone.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppeClone.shoppeClone.converter.address.AddressConverter;
import com.shoppeClone.shoppeClone.dto.address.AddressDTO;
import com.shoppeClone.shoppeClone.entity.AddressEntity;
import com.shoppeClone.shoppeClone.exception.ValidateException;
import com.shoppeClone.shoppeClone.respository.address.AddressRepository;
import com.shoppeClone.shoppeClone.service.AddressService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AddressServiceImpl implements AddressService{

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private AddressConverter addressConverter; 
	
	@Override
	public AddressDTO createAddress(AddressDTO addressDTO) {
		// Dto -> Entity
		AddressEntity newAddressEntity = addressConverter.toEntity(addressDTO);
		addressRepository.save(newAddressEntity);
		// Entity -> dto 
		AddressDTO resultDto = addressConverter.toDTO(newAddressEntity);
		
		return resultDto;
	}

	@Override
	public AddressDTO updateAddress(Long addressId, AddressDTO addressDTO) {
		AddressEntity addressEntity = addressRepository
				.findById(addressId)
				.orElseThrow(() -> new ValidateException("Không tìm thấy id của address cần sửa"));
		addressConverter.toEntity(addressEntity, addressDTO);
		addressRepository.save(addressEntity);
		return addressConverter.toDTO(addressEntity);
	}


}
