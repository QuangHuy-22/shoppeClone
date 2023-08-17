package com.shopeeClone.shopeeClone.api.province;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopeeClone.shopeeClone.dto.PageDTO;
import com.shopeeClone.shopeeClone.dto.province.ProvinceDTO;
import com.shopeeClone.shopeeClone.service.ProvinceService;

@RestController
@RequestMapping("/admin/api/v1/provinces")
public class PronvinceV1Api {

	@Autowired
	private ProvinceService provinceService;
	 
	@PostMapping
	public  ProvinceDTO createProvince(@RequestBody ProvinceDTO dto) {
		return provinceService.createProvince(dto);
	} 
	
	@PutMapping("{provinceId}")
	public ProvinceDTO updateProvince(
			@PathVariable(value = "provinceId") Long provinceId,
			@RequestBody ProvinceDTO dto) {
		
		return provinceService.updateProvince(provinceId, dto);
	}
	
	@DeleteMapping("{provinceId}")
	public void deleteProvince(
			@PathVariable(value = "provinceId") Long provinceId) {
		 provinceService.deleteProvince(provinceId);
	}
	
	@GetMapping
	public PageDTO<ProvinceDTO> getProvince(
			@RequestParam Map<String, String> params) {
		return provinceService.getProvinces(params);
	}
}
