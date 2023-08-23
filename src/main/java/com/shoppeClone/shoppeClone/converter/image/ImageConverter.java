package com.shoppeClone.shoppeClone.converter.image;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.shoppeClone.shoppeClone.dto.images.ImageDTO;
import com.shoppeClone.shoppeClone.entity.ImageEntity;


@Component
public class ImageConverter {

	public ImageDTO toDTO(ImageEntity imageEntity) {
		ImageDTO imageDTO = new ImageDTO();
		imageDTO.setDescription(imageEntity.getDescription());
		imageDTO.setImageId(imageEntity.getImageId());
		imageDTO.setUrl(imageEntity.getUrl());
		return imageDTO;
	}
	
	public ImageEntity toEntity(ImageDTO imageDTO) {
		ImageEntity imageEntity = new ImageEntity();
		imageEntity.setDescription(imageDTO.getDescription());
		imageEntity.setUrl(imageDTO.getUrl());
		
		return imageEntity;
	}
	
	public List<ImageDTO> toDTOList(List<ImageEntity> imageEntities){
		List<ImageDTO> imageDTOList = new ArrayList<>();
		if (imageEntities != null) {
			for (ImageEntity imageEntity : imageEntities) {
				imageDTOList.add(toDTO(imageEntity));
			}
		}
		return imageDTOList;
	}
	
	public List<ImageEntity> toEntityList(List<ImageDTO> imageDTOs){
		List<ImageEntity> imageEntitieList = new ArrayList<>();
		for (ImageDTO imageDTO : imageDTOs) {
			imageEntitieList.add(toEntity(imageDTO));
		}
		return imageEntitieList;
	}
}