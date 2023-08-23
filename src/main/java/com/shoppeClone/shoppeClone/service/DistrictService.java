package com.shoppeClone.shoppeClone.service;

import java.util.List;
import java.util.Map;

import com.shoppeClone.shoppeClone.dto.PageDTO;
import com.shoppeClone.shoppeClone.dto.district.DistrictDTO;

public interface DistrictService {
	DistrictDTO createDistrict(DistrictDTO dto);
    
    DistrictDTO updateDistrict(Long districtId, DistrictDTO dto);
    
    void deleteDistrict(Long districtId);
    
    List<DistrictDTO> getAll();
    
    PageDTO<DistrictDTO> getDistricts(Map<String, String> params);
    
    DistrictDTO getDistrictByDistrictId(Long districtId);
}
