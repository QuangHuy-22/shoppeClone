package com.shoppeClone.shoppeClone.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shoppeClone.shoppeClone.converter.image.ImageConverter;
import com.shoppeClone.shoppeClone.dto.images.ImageDTO;
import com.shoppeClone.shoppeClone.entity.ImageEntity;
import com.shoppeClone.shoppeClone.entity.ProductEntity;
import com.shoppeClone.shoppeClone.exception.ValidateException;
import com.shoppeClone.shoppeClone.respository.image.ImageRepository;
import com.shoppeClone.shoppeClone.respository.product.ProductRepository;
import com.shoppeClone.shoppeClone.service.ImageService;
import com.shoppeClone.shoppeClone.utils.AppStringUtils;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ImageServiceImpl implements ImageService{

	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ImageConverter imageConverter;
	
	@Override
	public ImageDTO saveImage(MultipartFile file, String description, Long productId) {
	
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
		
		ProductEntity product = productRepository.findById(productId)
	            .orElseThrow(() -> new ValidateException("Không tìm thấy sản phẩm"));
	    imageEntity.setProduct(product);
	    imageRepository.save(imageEntity);
	    
		// convert entity -> dto
		ImageDTO imageDTO = new ImageDTO();
		imageDTO.setDescription(imageEntity.getDescription());
		imageDTO.setUrl(imageEntity.getUrl());
		imageDTO.setImageId(imageEntity.getImageId());
		return imageDTO;
		
	}

	@Override
	public ImageDTO createImage(ImageDTO dto) {
		// Dkien kiểm tra xem có nguồn không.
		String url = dto.getUrl();
		if (!AppStringUtils.hasText(url)) {
			throw new ValidateException("Không tìm thấy đường link image");
		}
		//Dto -> Entity
		ImageEntity imageEntity = imageConverter.toEntity(dto);
		imageRepository.save(imageEntity); //lưu vào database
		
		// entity -> dto
		ImageDTO resultDto = imageConverter.toDTO(imageEntity);
		
		return resultDto;
	}

	@Override
	public ImageDTO updateImage(Long imageId, ImageDTO dto) {
		// validate 
		ImageEntity imageEntity = imageRepository
				.findById(imageId)
				.orElseThrow(() -> new ValidateException("Không tìm thấy id của image để sửa"));
		// DTO -> Entity
		imageConverter.toEntity(imageEntity, dto);
		imageRepository.save(imageEntity);
		
		return imageConverter.toDTO(imageEntity);
	}

	@Override
	public void deleteImage(Long imageId) {
		// Validate
		ImageEntity imageEntity = imageRepository
				.findById(imageId)
				.orElseThrow(() -> new ValidateException("Không tìm thấy id để xóa anh"));
		imageRepository.delete(imageEntity);	
	}

	@Override
	public List<ImageDTO> getAll() {
		List<ImageEntity> imageEntities = imageRepository.findAll();
		List<ImageDTO> imageDTOs = imageConverter.toDTOList(imageEntities);
		return imageDTOs;
	}
	
	@Override
	public ImageDTO getByIdImage(Long Id) {
		ImageEntity imageEntity = imageRepository.findById(Id)
				.orElseThrow(() -> new ValidateException("Không có Id này trong bộ nhớ DB"));
		imageRepository.save(imageEntity);
		return imageConverter.toDTO(imageEntity);
	}
	
}

	

