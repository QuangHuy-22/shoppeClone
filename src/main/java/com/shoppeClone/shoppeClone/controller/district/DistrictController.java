package com.shoppeClone.shoppeClone.controller.district;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppeClone.shoppeClone.dto.district.DistrictDTO;
import com.shoppeClone.shoppeClone.service.DistrictService;

@Controller
@RequestMapping("admin/districts")
public class DistrictController {
	
	@Autowired
	private DistrictService districtService;

	@GetMapping("add")
	public String showAddDistrictPage() {
	    return "admin/district/add-district";
	}

	@GetMapping("search")
	public String showSearchDistrictPage() {
	    return "admin/district/search-district";
	}

	@GetMapping("{districtId}")
	public String showUpdateDistrictPage(
	        @PathVariable String districtId, Model model) {
	    DistrictDTO districtDTO = districtService.getDistrictByDistrictId(Long.parseLong(districtId));
	    model.addAttribute("district", districtDTO);
	    return "admin/district/update-district";
	}

	
}