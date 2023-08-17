package com.shoppeClone.shoppeClone.controller.province;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/provinces")
public class ProvinceController {

	@GetMapping("add")
	public String showAddProvincePage() {
		return "admin/province/add-province";
	}
}
