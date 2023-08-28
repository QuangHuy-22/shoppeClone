package com.shoppeClone.shoppeClone.api.supplier;

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
import com.shoppeClone.shoppeClone.dto.supplier.SupplierDTO;
import com.shoppeClone.shoppeClone.service.SupplierService;

@RestController
@RequestMapping("admin/api/v1/suppliers")
public class SupplierV1Api {
	@Autowired
	private SupplierService supplierService;
	
	@PostMapping
    public SupplierDTO createSupplier(@RequestBody SupplierDTO dto) {
        return supplierService.createSupplier(dto);
    }

    @PutMapping("{supplierId}")
    public SupplierDTO updateSupplier(@PathVariable(value = "supplierId") Long supplierId,
                                      @RequestBody SupplierDTO dto) {
        return supplierService.updateSupplier(supplierId, dto);
    }

    @GetMapping
    public PageDTO<SupplierDTO> getSuppliers(@RequestParam Map<String, String> params) {
        return supplierService.getSuppliers(params);
    }

    @DeleteMapping("{supplierId}")
    public void deleteSupplierById(@PathVariable(value = "supplierId") Long supplierId) {
        supplierService.deleteSupplier(supplierId);
    }
    
    @GetMapping("{supplierId}")
    public SupplierDTO getSupplierById(@PathVariable(value = "supplierId") Long supplierId) {
            return supplierService.getSupplierById(supplierId);
    }
}
