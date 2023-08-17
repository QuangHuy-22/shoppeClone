package com.shoppeClone.shoppeClone.controller.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppeClone.shoppeClone.dto.supplier.SupplierDTO;
import com.shoppeClone.shoppeClone.service.SupplierService;

@Controller
@RequestMapping("admin/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("add")
	public String showAddSupplierPage() {
		return "admin/supplier/add-supplier";
	}
	
	@GetMapping("search")
	public String showSearchSuppliersPage() {
		return "admin/supplier/search-supplier";
	}
	
	@GetMapping("{supplierId}")
	public String showUpdateSupplierPage(
			@PathVariable Long supplierId,
			Model model
			) {
		SupplierDTO supplierDTO = supplierService
				.getSupplierById(supplierId);
		model.addAttribute("supplier", supplierDTO);
		return "admin/supplier/update-supplier";
	}
}
