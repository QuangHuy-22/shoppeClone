package com.shoppeClone.shoppeClone.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.shoppeClone.shoppeClone.dto.images.ImageDTO;

public interface ImageService {

	ImageDTO saveImage(MultipartFile file, String description, Long productId);
	
	ImageDTO createImage(ImageDTO dto);
	
	ImageDTO updateImage(Long imageId, ImageDTO dto);
	
	void deleteImage(Long imageId);
	
	List<ImageDTO> getAll();
	
	ImageDTO getByIdImage(Long Id);
}
