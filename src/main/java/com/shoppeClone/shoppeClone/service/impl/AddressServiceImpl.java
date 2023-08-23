package com.shoppeClone.shoppeClone.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppeClone.shoppeClone.converter.Address.AddressConverter;
import com.shoppeClone.shoppeClone.dto.address.AddressDTO;
import com.shoppeClone.shoppeClone.entity.AddressEntity;
import com.shoppeClone.shoppeClone.exception.ValidateException;
import com.shoppeClone.shoppeClone.respository.address.AddressRepository;
import com.shoppeClone.shoppeClone.respository.district.DistrictRepository;
import com.shoppeClone.shoppeClone.respository.pronvice.ProvinceRepository;
import com.shoppeClone.shoppeClone.respository.ward.WardRepository;
import com.shoppeClone.shoppeClone.service.AddressService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressConverter addressConverter;
	
	@Autowired
	private DistrictRepository districtRepostory;
	@Autowired
	private ProvinceRepository provinceRepostory;
	@Autowired
	private WardRepository wardRepostory;
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
