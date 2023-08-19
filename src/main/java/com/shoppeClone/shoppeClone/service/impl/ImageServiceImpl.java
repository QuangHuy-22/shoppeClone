package com.shoppeClone.shoppeClone.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shoppeClone.shoppeClone.dto.ImageDTO;
import com.shoppeClone.shoppeClone.entity.ImageEntity;
import com.shoppeClone.shoppeClone.exception.ValidateException;
import com.shoppeClone.shoppeClone.respository.ImageRepository;
import com.shoppeClone.shoppeClone.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService{
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Override
	public ImageDTO saveImage(MultipartFile file, String description) {
		String fileName = file.getOriginalFilename();
		try {
			// Lưu file
			InputStream inputStream = file.getInputStream();
			byte[] buffer = new byte[inputStream.available()];
			inputStream.read(buffer);
			File newFile = new File("src/main/resources/static/images/" + fileName);
			OutputStream outputStream
				= new FileOutputStream(newFile);
			outputStream.write(buffer);
		} catch (IOException e) {
			throw new ValidateException("Server error");
		}
		// ImageEntity
		ImageEntity imageEntity = new ImageEntity();
		imageEntity.setUrl("/images/" + fileName);
		imageEntity.setDescription(description);
		imageRepository.save(imageEntity);
		// convert entity -> dto
		ImageDTO imageDTO = new ImageDTO();
		imageDTO.setDescription(imageEntity.getDescription());
		imageDTO.setUrl(imageEntity.getUrl());
		imageDTO.setImageId(imageEntity.getImageId());
		return imageDTO;
		
	}

}
