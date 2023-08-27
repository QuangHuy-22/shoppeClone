package com.shoppeClone.shoppeClone.api.district;

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

import com.shoppeClone.shoppeClone.dto.PageDTO;
import com.shoppeClone.shoppeClone.dto.district.DistrictDTO;
import com.shoppeClone.shoppeClone.service.DistrictService;

@RestController
@RequestMapping("admin/api/v1/districts")
public class DistrictV1Api {
	
	@Autowired
	private DistrictService districtService;

	@PostMapping()
	public DistrictDTO createDistrict(@RequestBody DistrictDTO dto) {
	    return districtService.createDistrict(dto);
	} 

	@PutMapping("{districtId}")
	public DistrictDTO updateDistrict(
	        @PathVariable(value = "districtId") Long districtId,
	        @RequestBody DistrictDTO dto) {
	    return districtService.updateDistrict(districtId, dto);
	}

	@DeleteMapping("{districtId}")
	public void deleteDistrict(
	        @PathVariable(value = "districtId") Long districtId) {
	    districtService.deleteDistrict(districtId);
	}

	@GetMapping
	public PageDTO<DistrictDTO> getDistricts(
	        @RequestParam Map<String, String> params) {
	    return districtService.getDistricts(params);
	}
	
	 @GetMapping("{districtId}")
	    public DistrictDTO getDistrictById(@PathVariable Long districtId) {
	        return districtService.getDistrictByDistrictId(districtId);
	    }
	
}
