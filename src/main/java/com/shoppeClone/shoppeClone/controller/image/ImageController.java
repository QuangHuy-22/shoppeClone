package com.shoppeClone.shoppeClone.controller.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shoppeClone.shoppeClone.dto.images.ImageDTO;
import com.shoppeClone.shoppeClone.service.ImageService;


@RestController
@RequestMapping("images")
public class ImageController {
	@Autowired
	private ImageService imageService;
	
	@PostMapping(path = "fileUpLoad", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ImageDTO upLoadFile(
			@RequestParam("file") MultipartFile multipartFile,
			@RequestParam(value = "description", required = false)
			String description,
			@RequestParam("productId") Long productId
			)
	{
		return imageService.saveImage(multipartFile, description, productId);
	}

}
