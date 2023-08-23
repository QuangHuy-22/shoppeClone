package com.shoppeClone.shoppeClone.converter.district;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.shoppeClone.shoppeClone.dto.district.DistrictDTO;
import com.shoppeClone.shoppeClone.entity.DistrictEntity;

@Component
public class DistrictConverter {

    public DistrictDTO toDTO(DistrictEntity districtEntity) {
        DistrictDTO districtDTO = new DistrictDTO();
        districtDTO.setDistrictId(districtEntity.getDistrictId());
        districtDTO.setName(districtEntity.getName());
        districtDTO.setCode(districtEntity.getCode());
        districtDTO.setCreateDate(districtEntity.getCreateDate());
        districtDTO.setModifierDate(districtEntity.getModifierDate());
        return districtDTO;
    }

    public DistrictEntity toEntity(DistrictDTO districtDTO) {
        DistrictEntity districtEntity = new DistrictEntity();
        districtEntity.setName(districtDTO.getName());
        districtEntity.setCode(districtDTO.getCode());
        districtEntity.setCreateDate(districtDTO.getCreateDate());
        districtEntity.setModifierDate(districtDTO.getModifierDate());
        return districtEntity;
    }

    public DistrictEntity toEntity(DistrictEntity districtEntity, DistrictDTO districtDTO) {
        districtEntity.setName(districtDTO.getName());
        districtEntity.setCode(districtDTO.getCode());
        districtEntity.setCreateDate(districtDTO.getCreateDate());
        districtEntity.setModifierDate(districtDTO.getModifierDate());
        return districtEntity;
    }

    public List<DistrictDTO> toDTOList(List<DistrictEntity> districtEntities) {
        List<DistrictDTO> districtDTOList = new ArrayList<>();
        for (DistrictEntity districtEntity : districtEntities) {
            districtDTOList.add(toDTO(districtEntity));
        }
        return districtDTOList;
    }

    public List<DistrictEntity> toEntityList(List<DistrictDTO> districtDTOs) {
        List<DistrictEntity> districtEntityList = new ArrayList<>();
        for (DistrictDTO districtDTO : districtDTOs) {
            districtEntityList.add(toEntity(districtDTO));
        }
        return districtEntityList;
    }

   

	
}
