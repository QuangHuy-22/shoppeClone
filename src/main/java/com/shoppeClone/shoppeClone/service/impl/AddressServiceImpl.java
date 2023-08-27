package com.shoppeClone.shoppeClone.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppeClone.shoppeClone.converter.Address.AddressConverter;
import com.shoppeClone.shoppeClone.dto.PageDTO;
import com.shoppeClone.shoppeClone.dto.address.AddressDTO;
import com.shoppeClone.shoppeClone.entity.AddressEntity;
import com.shoppeClone.shoppeClone.exception.ValidateException;
import com.shoppeClone.shoppeClone.respository.address.AddressRepository;
import com.shoppeClone.shoppeClone.respository.district.DistrictRepository;
import com.shoppeClone.shoppeClone.respository.pronvice.ProvinceRepository;
import com.shoppeClone.shoppeClone.respository.ward.WardRepository;
import com.shoppeClone.shoppeClone.service.AddressService;
import com.shoppeClone.shoppeClone.utils.AppStringUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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
	
	@Autowired
	private EntityManager entityManager;
	
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
		AddressEntity addressEntity = addressRepository
				.findById(addressId)
				.orElseThrow(()-> new ValidateException("Address not found"));
		
			addressConverter.toEntity(addressEntity, addressDTO);
			addressRepository.save(addressEntity);
		return addressConverter.toDTO(addressEntity);
	}

	@Override
	public void deleteAddress(Long AddressIid) {
		addressRepository
		.findById(AddressIid)
		.orElseThrow(() -> new ValidateException("Address not found"));
		
		addressRepository.deleteById(AddressIid);
	}

	@Override
	public List<AddressDTO> getAddress() {
		List<AddressEntity> addressEntity = addressRepository.findAll();
		List<AddressDTO> addressDtos = addressConverter.toDTOList(addressEntity);
		return addressDtos;
	}
	@Override
	public PageDTO<AddressDTO> getPageAddress(Map<String, String> params) {
		
		String limitstr = params.get("limit");
		String pagestr = params.get("page");
		Integer page = 1;
		Integer limit = 10;
		
		if (AppStringUtils.hasText(pagestr)) {
			page = Integer.valueOf(pagestr);
		}
		if (AppStringUtils.hasText(limitstr)) {
			limit = Integer.valueOf(limitstr);
		}
		
		// Lấy dữ liệu từ database bằng cậu lệnh query và vứt trong StringBuiler
		
		StringBuilder selectQueryBuilder = 
				new StringBuilder("select p from AddressEntity p ");
		
		StringBuilder countQueryBuilder = 
				new StringBuilder("select count(p.addressId) from AddressEntity p ");
		
		String Id = params.get("addressId");
		if (AppStringUtils.hasText(Id)) {
			selectQueryBuilder.append("where p.addressId like :addressId");
			countQueryBuilder.append("where p.addressId like :addressId");
		}
		
		TypedQuery<AddressEntity> selectQuery = 
				entityManager.createQuery(selectQueryBuilder.toString(), AddressEntity.class);
		
		TypedQuery<Long> countQuery = 
				entityManager.createQuery(countQueryBuilder.toString(), Long.class);
		
		Integer firsAddress = (page - 1) * limit;
		
		if (AppStringUtils.hasText(Id)) {
			selectQuery.setParameter("addressId", "%" + Id + "%");
			countQuery.setParameter("addressId","%" + Id + "%");
		}
		
		selectQuery.setFirstResult(firsAddress);
		selectQuery.setMaxResults(limit);
		
		List<AddressEntity> addressEntities = selectQuery.getResultList();
		Long totalItems = countQuery.getSingleResult();
		
		List<AddressDTO> addressDTOs = addressConverter.toDTOList(addressEntities);
		
		return new PageDTO<>(page, limit, totalItems, addressDTOs);
	}
	

}
