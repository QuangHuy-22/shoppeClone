package com.shoppeClone.shoppeClone.service.impl;

import java.util.List;
import java.util.Map;

import com.shoppeClone.shoppeClone.exception.ValidateException;
import com.shoppeClone.shoppeClone.respository.address.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppeClone.shoppeClone.converter.address.AddressConverter;
import com.shoppeClone.shoppeClone.dto.PageDTO;
import com.shoppeClone.shoppeClone.dto.address.AddressDTO;
import com.shoppeClone.shoppeClone.dto.address.CreateAddressDTO;
import com.shoppeClone.shoppeClone.dto.order.OrderDTO;
import com.shoppeClone.shoppeClone.entity.AddressEntity;
import com.shoppeClone.shoppeClone.entity.OrderEntity;

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
	private AddressRepository addressRepository;
	@Autowired 
	private EntityManager entityManager;
	@Override
	public AddressDTO createAddress(CreateAddressDTO dto) {
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
	public AddressDTO updateAddress(Long addressId, CreateAddressDTO addressDTO) {
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

//	@Override
//	public List<CreateAddressDTO> getAddress() {
//		List<AddressEntity> addressEntity = addressRepository.findAll();
//		List<AddressDTO> addressDtos = addressConverter.toDTOList(addressEntity);
//		return addressDtos;
//	}

	@Override
	public PageDTO<AddressDTO> getOrders(Map<String, String> params) {
		System.out.println(params);
		String pageStr = params.get("page");
		String limitStr = params.get("limit");
		Integer page = 1;
		Integer limit = 10;
		if (AppStringUtils.hasText(pageStr)) {
			page = Integer.valueOf(pageStr);
		}
		if (AppStringUtils.hasText(limitStr)) {
			limit = Integer.valueOf(limitStr);
		}
		// lấy dữ liệu
		// đếm dữ liệu
		StringBuilder selectQueryBuilder = new StringBuilder("SELECT c FROM AddressEntity c");
		StringBuilder countQueryBuilder 
				= new StringBuilder("SELECT COUNT(c.addressId) FROM AddressEntity c");
		
		String name = params.get("orderId");
		if (AppStringUtils.hasText(name)) {
			selectQueryBuilder.append(" WHERE c.addressId LIKE :addressId" );
			countQueryBuilder.append(" WHERE c.addressId LIKE :addressId");
		}
	
		TypedQuery<AddressEntity> selectQuery 
				= entityManager.createQuery(selectQueryBuilder.toString(), AddressEntity.class);
		
		TypedQuery<Long> countQuery 
			= entityManager.createQuery(countQueryBuilder.toString(), Long.class);
		Integer firstItems = (page - 1) * limit;
		
		if (AppStringUtils.hasText(name)) {
			selectQuery.setParameter("addressId", "%" + name + "%");
			countQuery.setParameter("addressId", "%" + name + "%");
		}
		
		selectQuery.setFirstResult(firstItems);
		selectQuery.setMaxResults(limit);
		
		List<AddressEntity> addressEntities = selectQuery.getResultList();
		Long totalItems = countQuery.getSingleResult();
		
		// entity -> dto
		List<AddressDTO> dtos = addressConverter.toDTOList(addressEntities);
		
		
		return new PageDTO<>(page, limit, totalItems, dtos);
	
	}

	@Override
	public List<CreateAddressDTO> getAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AddressDTO getOrderbyOrderId(Long addressId) {
		AddressEntity orElseThrow = addressRepository.findById(addressId)
				.orElseThrow(() -> new ValidateException("Khong tim thấy addressId"));
		  
		return addressConverter.toDTO(orElseThrow);
	}

	

	

}
