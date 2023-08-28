package com.shoppeClone.shoppeClone.api.image;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shoppeClone.shoppeClone.dto.images.ImageDTO;
import com.shoppeClone.shoppeClone.service.ImageService;



@RestController
@RequestMapping("images")
public class ImageV1Api {
	
	@Autowired
	private ImageService imageService;
	
	@PostMapping
	public ImageDTO createImage(
			@RequestBody ImageDTO dto
			) 
	{
		return imageService.createImage(dto);
	}
	
	@GetMapping
	public List<ImageDTO> getImage()		
	{
		return imageService.getAll();
	}
	@GetMapping("{imageId}")
	public ImageDTO getByIdImage(
			@PathVariable(value = "imageId") Long Id
			)
	{
		return imageService.getByIdImage(Id);
	}
	
	@PutMapping("{imageId}")
	public ImageDTO updateImage(
			@RequestBody ImageDTO dto,
			@PathVariable(value = "imageId") Long imageId
			)
	{
		return imageService.updateImage(imageId, dto);
	}
	
	@DeleteMapping("{imageId}")
	public void deleteImage(
			@PathVariable(value = "imageId") Long imageId
			)
	{
		 imageService.deleteImage(imageId);
	}
}
