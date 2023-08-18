package com.shopeeClone.shopeeClone.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopeeClone.shopeeClone.converter.Address.AddressConverter;
import com.shopeeClone.shopeeClone.dto.PageDTO;
import com.shopeeClone.shopeeClone.dto.address.AddressDTO;
import com.shopeeClone.shopeeClone.dto.category.CategoryDTO;
import com.shopeeClone.shopeeClone.entity.AddressEntity;
import com.shopeeClone.shopeeClone.entity.CategoryEntity;
import com.shopeeClone.shopeeClone.entity.DistrictEntity;
import com.shopeeClone.shopeeClone.entity.ProvinceEntity;
import com.shopeeClone.shopeeClone.entity.WardEntity;
import com.shopeeClone.shopeeClone.exeption.ValidateException;
import com.shopeeClone.shopeeClone.repository.address.AddressRepository;
import com.shopeeClone.shopeeClone.repository.district.districtRepostory;
import com.shopeeClone.shopeeClone.repository.province.provinceRepostory;
import com.shopeeClone.shopeeClone.repository.ward.wardRepostory;
import com.shopeeClone.shopeeClone.service.AddressService;
import com.shopeeClone.shopeeClone.utils.AppStringUtils;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressConverter addressConverter;
	
	@Autowired
	private districtRepostory districtRepostory;
	@Autowired
	private provinceRepostory provinceRepostory;
	@Autowired
	private wardRepostory wardRepostory;
	@Autowired
	private AddressRepository addressRepository;
	@Override
	public AddressDTO createAddress(AddressDTO dto) {
		AddressEntity newAddressEntity = addressConverter.toEntity(dto);
		addressRepository.save(newAddressEntity);
		AddressDTO result = addressConverter.toDTO(newAddressEntity);
		return result;
	}

//	@Override
//	public PageDTO<AddressDTO> getAddress(Map<String, String> params) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public AddressDTO updateAddress(Long addressId, AddressDTO addressDTO) {
		AddressEntity addressEntity = addressRepository.findById(addressId).orElseThrow(()-> new ValidateException("Address not found"));
		
		addressConverter.toEntity(addressEntity, addressDTO);
		addressRepository.save(addressEntity);
		return addressConverter.toDTO(addressEntity);
	}

	@Override
	public void deleteAddress(Long AddressIid) {
		addressRepository
		.findById(AddressIid).orElseThrow(() -> new ValidateException("Address not found"));
		addressRepository.deleteById(AddressIid);
	}

	@Override
	public List<AddressDTO> getAddress() {
		List<AddressEntity> addressEntity = addressRepository.findAll();
		List<AddressDTO> addressDtos = addressConverter.toDTOList(addressEntity);
		return addressDtos;
	}

	

}
