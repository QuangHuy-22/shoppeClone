package com.shoppeClone.shoppeClone.service;

import java.util.Map;

import com.shoppeClone.shoppeClone.dto.PageDTO;
import com.shoppeClone.shoppeClone.dto.supplier.SupplierDTO;

public interface SupplierService {
	
    SupplierDTO getSupplierById(Long id);
    
    SupplierDTO createSupplier(SupplierDTO supplierDto);
    
    SupplierDTO updateSupplier(Long id, SupplierDTO supplierDto);
    
    void deleteSupplier(Long id);

	PageDTO<SupplierDTO> getSuppliers(Map<String, String> params);


}
