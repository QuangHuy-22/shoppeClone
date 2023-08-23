package com.shoppeClone.shoppeClone.converter.province;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.shoppeClone.shoppeClone.dto.province.ProvinceDTO;
import com.shoppeClone.shoppeClone.entity.ProvinceEntity;


@Component
public class ProvinceConverter {

	public ProvinceDTO toDTO(ProvinceEntity provinceEntity) {
		ProvinceDTO provinceDTO = new ProvinceDTO();
		provinceDTO.setProvinceId(provinceEntity.getProvinceId());
		provinceDTO.setName(provinceEntity.getName());
		provinceDTO.setCode(provinceEntity.getCode());
		provinceDTO.setCreatedDate(provinceEntity.getCreateDate());
		return provinceDTO;
	}
	public ProvinceEntity toEntity(ProvinceDTO provinceDTO) {
		
		ProvinceEntity provinceEntity = new ProvinceEntity();
		provinceEntity.setName(provinceDTO.getName());
		provinceEntity.setCode(provinceDTO.getCode());
		provinceEntity.setCreateDate(provinceDTO.getCreatedDate());
		return provinceEntity;
	}
	
	public ProvinceEntity toEntity(ProvinceEntity provinceEntity, ProvinceDTO provinceDTO) {
		provinceEntity.setName(provinceDTO.getName());
		provinceEntity.setCode(provinceDTO.getCode());
		provinceEntity.setCreateDate(provinceDTO.getCreatedDate());
		return provinceEntity;
	}
	
	public List<ProvinceDTO> toDtoList(List<ProvinceEntity> provinceEntities){
		List<ProvinceDTO> provinceDTOList = new ArrayList<>();
		for (ProvinceEntity provinceEntity : provinceEntities) {
			provinceDTOList.add(toDTO(provinceEntity));
		}
		return provinceDTOList;
	}
	public List<ProvinceEntity> toEntityList(List<ProvinceDTO> provinceDTOs){
		List<ProvinceEntity> provinceEntitieList = new ArrayList<>();
		for (ProvinceDTO provinceDTO : provinceDTOs) {
			provinceEntitieList.add(toEntity(provinceDTO));
		}
		return provinceEntitieList;
	}
}
