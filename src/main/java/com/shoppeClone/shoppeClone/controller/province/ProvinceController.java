package com.shoppeClone.shoppeClone.controller.province;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppeClone.shoppeClone.dto.province.ProvinceDTO;
import com.shoppeClone.shoppeClone.service.ProvinceService;

@Controller
@RequestMapping("admin/provinces")
public class ProvinceController {
	
	@Autowired
	private ProvinceService provinceService;

	@GetMapping("add")
	public String showAddProvincePage() {
		return "admin/province/add-province";
	}
	@GetMapping("search")
	public String showSearchProvincePage() {
		return "admin/province/search-province";
	}
	
	@GetMapping("{provinceId}")
	public String showUpdateProvincePage(
			@PathVariable String provinceId, Model model) {
		ProvinceDTO provinceDTO = provinceService.getProvinceByProvinceId(Long.parseLong(provinceId));
		model.addAttribute("province",provinceDTO);
		return "admin/province/update-province";
	}
	
}
