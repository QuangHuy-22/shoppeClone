package com.shoppeClone.shoppeClone.service;

import org.springframework.web.multipart.MultipartFile;

import com.shoppeClone.shoppeClone.dto.images.ImageDTO;

public interface ImageService {

	ImageDTO saveImage(MultipartFile file, String description, Long productId);
}
