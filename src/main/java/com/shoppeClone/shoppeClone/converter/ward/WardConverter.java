package com.shoppeClone.shoppeClone.converter.ward;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.shoppeClone.shoppeClone.dto.ward.WardDTO;
import com.shoppeClone.shoppeClone.entity.WardEntity;

@Component
public class WardConverter {

    public WardDTO toDTO(WardEntity wardEntity) {
        WardDTO wardDTO = new WardDTO();
        wardDTO.setWardId(wardEntity.getWardId());
        wardDTO.setName(wardEntity.getName());
        wardDTO.setCode(wardEntity.getCode());
        wardDTO.setCreateDate(wardEntity.getCreateDate());
        wardDTO.setModifierDate(wardEntity.getModifierDate());
        return wardDTO;
    }

    public WardEntity toEntity(WardDTO wardDTO) {
        WardEntity wardEntity = new WardEntity();
        wardEntity.setName(wardDTO.getName());
        wardEntity.setCode(wardDTO.getCode());
        wardEntity.setCreateDate(wardDTO.getCreateDate());
        wardEntity.setModifierDate(wardDTO.getModifierDate());
        return wardEntity;
    }

    public WardEntity toEntity(WardEntity wardEntity, WardDTO wardDTO) {
        wardEntity.setName(wardDTO.getName());
        wardEntity.setCode(wardDTO.getCode());
        wardEntity.setCreateDate(wardDTO.getCreateDate());
        wardEntity.setModifierDate(wardDTO.getModifierDate());
        return wardEntity;
    }

    public List<WardDTO> toDTOList(List<WardEntity> wardEntities) {
        List<WardDTO> wardDTOList = new ArrayList<>();
        for (WardEntity wardEntity : wardEntities) {
            wardDTOList.add(toDTO(wardEntity));
        }
        return wardDTOList;
    }

    public List<WardEntity> toEntityList(List<WardDTO> wardDTOs) {
        List<WardEntity> wardEntityList = new ArrayList<>();
        for (WardDTO wardDTO : wardDTOs) {
            wardEntityList.add(toEntity(wardDTO));
        }
        return wardEntityList;
    }
}
