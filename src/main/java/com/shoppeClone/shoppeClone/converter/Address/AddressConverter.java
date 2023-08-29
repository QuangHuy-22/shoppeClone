package com.shoppeClone.shoppeClone.converter.Address;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shoppeClone.shoppeClone.converter.district.DistrictConverter;
import com.shoppeClone.shoppeClone.converter.province.ProvinceConverter;
import com.shoppeClone.shoppeClone.converter.ward.WardConverter;
import com.shoppeClone.shoppeClone.dto.address.AddressDTO;
import com.shoppeClone.shoppeClone.dto.address.CreateAddressDTO;
import com.shoppeClone.shoppeClone.entity.AddressEntity;
import com.shoppeClone.shoppeClone.entity.DistrictEntity;
import com.shoppeClone.shoppeClone.entity.ProvinceEntity;
import com.shoppeClone.shoppeClone.entity.WardEntity;
import com.shoppeClone.shoppeClone.exception.ValidateException;
import com.shoppeClone.shoppeClone.respository.district.DistrictRepository;
import com.shoppeClone.shoppeClone.respository.pronvice.ProvinceRepository;
import com.shoppeClone.shoppeClone.respository.ward.WardRepository;


@Component
public class AddressConverter {

	@Autowired
	private ProvinceConverter provinceConverter;
	@Autowired
	private DistrictConverter districtConverter;
	@Autowired
	private WardRepository wardRepostory;
	@Autowired
	private ProvinceRepository provinceRepostory;
	@Autowired
	private DistrictRepository districtRepostory;
	@Autowired
	private WardConverter wardConverter;
	public AddressDTO toDTO(AddressEntity addressEntity) {
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setAddressId(addressEntity.getAddressId());
		addressDTO.setDescription(addressEntity.getDescription());
		addressDTO.setDistrict(districtConverter.toDTO(addressEntity.getDistrict()));
		addressDTO.setProvince(provinceConverter.toDTO(addressEntity.getProvince()));
		addressDTO.setWard(wardConverter.toDTO(addressEntity.getWard()));

		return addressDTO;
	}

	public AddressEntity toEntity(CreateAddressDTO addressDTO) {
		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setAddressId(addressDTO.getAddressId());
		addressEntity.setDescription(addressDTO.getDescription());

		Long provinceId = addressDTO.getProvince();
		Long districtId = addressDTO.getDistrict();
		Long wardId = addressDTO.getWard();
		//Kiem tra loi
		if (provinceId == null) {
			throw new ValidateException("Không tìm thấy Id");
		}
		if (districtId == null) {
			throw new ValidateException("Không tìm thấy Id");
		}
		if (wardId == null) {
			throw new ValidateException("không tìm thấy Id");
		}
		
		//Lấy Id từ db
		ProvinceEntity province = provinceRepostory
				.findById(provinceId).orElseThrow(() -> new ValidateException("Not found"));
		addressEntity.setProvince(province);

		DistrictEntity district = districtRepostory
				.findById(districtId).orElseThrow(() -> new ValidateException("Not found"));
		addressEntity.setDistrict(district);

		WardEntity ward = wardRepostory
				.findById(wardId).orElseThrow(() -> new ValidateException("not found"));
	
		addressEntity.setWard(ward);

		return addressEntity;
	}

	public AddressEntity toEntity(AddressEntity addressEntity, CreateAddressDTO addressDto) {

		
		addressEntity.setDescription(addressDto.getDescription());

		Long provinceId = addressDto.getProvince();
		Long districtId = addressDto.getDistrict();
		Long wardId = addressDto.getWard();
		//Kiem tra loi
		if (provinceId == null) {
			throw new ValidateException("Không tìm thấy Id");
		}
		if (districtId == null) {
			throw new ValidateException("Không tìm thấy Id");
		}
		if (wardId == null) {
			throw new ValidateException("không tìm thấy Id");
		}
		
		//Lấy Id từ db
		ProvinceEntity province = provinceRepostory
				.findById(provinceId).orElseThrow(() -> new ValidateException("Not found"));
		addressEntity.setProvince(province);

		DistrictEntity district = districtRepostory
				.findById(districtId).orElseThrow(() -> new ValidateException("Not found"));
		addressEntity.setDistrict(district);

		WardEntity ward = wardRepostory
				.findById(wardId).orElseThrow(() -> new ValidateException("not found"));
	
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

	public List<AddressEntity> toEntityList(List<CreateAddressDTO> addressDTOList) {
		List<AddressEntity> addressEntities = new ArrayList<>();
		for (CreateAddressDTO addressDTO : addressDTOList) {
			addressEntities.add(toEntity(addressDTO));
		}
		return addressEntities;
	}

}
