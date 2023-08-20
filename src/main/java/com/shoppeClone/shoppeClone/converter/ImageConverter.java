package com.shoppeClone.shoppeClone.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.shoppeClone.shoppeClone.dto.ImageDTO;
import com.shoppeClone.shoppeClone.entity.ImageEntity;

@Component
public class ImageConverter {

    public ImageDTO toDTO(ImageEntity imageEntity) {
        if (imageEntity == null) {
            return null;
        }

        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setImageId(imageEntity.getImageId());
        imageDTO.setUrl(imageEntity.getUrl());
        imageDTO.setDescription(imageEntity.getDescription());

        return imageDTO;
    }
    

    public List<ImageDTO> toDTO(List<ImageEntity> imageEntities) {
        List<ImageDTO> imageDTOs = new ArrayList<>();

        if (imageEntities != null) {
            for (ImageEntity imageEntity : imageEntities) {
                imageDTOs.add(toDTO(imageEntity));
            }
        }

        return imageDTOs;
    }

    public ImageEntity toEntity(ImageDTO imageDTO) {
        if (imageDTO == null) {
            return null;
        }

        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setImageId(imageDTO.getImageId());
        imageEntity.setUrl(imageDTO.getUrl());
        imageEntity.setDescription(imageDTO.getDescription());

        return imageEntity;
    }
}
