package com.shoppeClone.shoppeClone.api.ward;

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
import com.shoppeClone.shoppeClone.dto.ward.WardDTO;
import com.shoppeClone.shoppeClone.service.WardService;

@RestController
@RequestMapping("admin/api/v1/wards")
public class WardV1Api {
	
	@Autowired
	private WardService wardService;

	@PostMapping()
	public WardDTO createWard(@RequestBody WardDTO dto) {
	    return wardService.createWard(dto);
	} 

	@PutMapping("{wardId}")
	public WardDTO updateWard(
	        @PathVariable(value = "wardId") Long wardId,
	        @RequestBody WardDTO dto) {
	    return wardService.updateWard(wardId, dto);
	}

	@DeleteMapping("{wardId}")
	public void deleteWard(
	        @PathVariable(value = "wardId") Long wardId) {
	    wardService.deleteWard(wardId);
	}

	@GetMapping
	public PageDTO<WardDTO> getWards(
	        @RequestParam Map<String, String> params) {
	    return wardService.getWards(params);
	}
	
	 @GetMapping("{wardId}")
	    public WardDTO getWardById(@PathVariable Long wardId) {
	        return wardService.getWardByWardId(wardId);
	    }
}
