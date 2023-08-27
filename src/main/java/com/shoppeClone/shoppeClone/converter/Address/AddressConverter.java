package com.shoppeClone.shoppeClone.converter.Address;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.shoppeClone.shoppeClone.dto.address.AddressDTO;
import com.shoppeClone.shoppeClone.dto.category.CategoryDTO;
import com.shoppeClone.shoppeClone.entity.AddressEntity;
import com.shoppeClone.shoppeClone.entity.CategoryEntity;
import com.shoppeClone.shoppeClone.entity.DistrictEntity;
import com.shoppeClone.shoppeClone.entity.ProvinceEntity;
import com.shoppeClone.shoppeClone.entity.WardEntity;

@Component
public class AddressConverter {

	public AddressDTO toDTO(AddressEntity addressEntity) {
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setAddressId(addressEntity.getAddressId());

		addressDTO.setDescription(addressEntity.getDescription());
		addressDTO.setDistrict(addressEntity.getDistrict().getDistrictId());
		addressDTO.setProvince(addressEntity.getProvince().getProvinceId());
		addressDTO.setWard(addressEntity.getWard().getWardId());

		return addressDTO;
	}

	public AddressEntity toEntity(AddressDTO addressDTO) {
		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setAddressId(addressDTO.getAddressId());
		addressEntity.setDescription(addressDTO.getDescription());

		ProvinceEntity province = new ProvinceEntity();
		province.setProvinceId(addressDTO.getProvince());
		addressEntity.setProvince(province);

		DistrictEntity district = new DistrictEntity();
		district.setDistrictId(addressDTO.getDistrict());
		addressEntity.setDistrict(district);

		WardEntity ward = new WardEntity();
		ward.setWardId(addressDTO.getWard());
		addressEntity.setWard(ward);

		return addressEntity;
	}

	public AddressEntity toEntity(AddressEntity addressEntity, AddressDTO addressDto) {

		addressEntity.setAddressId(addressDto.getAddressId());
		addressEntity.setDescription(addressDto.getDescription());

		ProvinceEntity province = new ProvinceEntity();
		province.setProvinceId(addressDto.getProvince());
		addressEntity.setProvince(province);

		DistrictEntity district = new DistrictEntity();
		district.setDistrictId(addressDto.getDistrict());
		addressEntity.setDistrict(district);

		WardEntity ward = new WardEntity();
		ward.setWardId(addressDto.getWard());
		addressEntity.setWard(ward);

		return addressEntity;
	}

	public List<AddressDTO> toDTOList(List<AddressEntity> addressEntitys) {
		List<AddressDTO> addressDTOList = new ArrayList<>();
		for (AddressEntity addressEntity : addressEntitys) {
			addressDTOList.add(toDTO(addressEntity));
		}
		return addressDTOList;
	}

	public List<AddressEntity> toEntityList(List<AddressDTO> addressDTOList) {
		List<AddressEntity> addressEntities = new ArrayList<>();
		for (AddressDTO addressDTO : addressDTOList) {
			addressEntities.add(toEntity(addressDTO));
		}
		return addressEntities;
	}

}
