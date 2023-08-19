package com.shoppeClone.shoppeClone.service;

import org.springframework.web.multipart.MultipartFile;

import com.shoppeClone.shoppeClone.dto.ImageDTO;

public interface ImageService {

	ImageDTO saveImage(MultipartFile file, String description);
	
}
