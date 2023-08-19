package com.shoppeClone.shoppeClone.converter.address;

import org.springframework.stereotype.Component;

import com.shoppeClone.shoppeClone.dto.address.AddressDTO;
import com.shoppeClone.shoppeClone.entity.AddressEntity;



@Component
public class AddressConverter {

	public AddressDTO toDTO(AddressEntity addressEntity) {
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setAddressId(addressEntity.getAddressId());
		addressDTO.setDescription(addressEntity.getDescription());
		return addressDTO;
		
	}
	
	public AddressEntity toEntity(AddressDTO addressDTO) {
		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setDescription(addressDTO.getDescription());
		return addressEntity;
	}
	
	public AddressEntity toEntity(AddressEntity addressEntity,  AddressDTO addressDTO) {
		addressEntity.setDescription(addressDTO.getDescription());
		return addressEntity;
	}
	
}
