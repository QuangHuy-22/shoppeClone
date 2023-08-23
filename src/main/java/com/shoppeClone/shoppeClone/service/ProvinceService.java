package com.shoppeClone.shoppeClone.service;

import java.util.List;
import java.util.Map;

import com.shoppeClone.shoppeClone.dto.PageDTO;
import com.shoppeClone.shoppeClone.dto.province.ProvinceDTO;

public interface ProvinceService {

	ProvinceDTO createProvince(ProvinceDTO dto);
	
	ProvinceDTO updateProvince(Long provinceId, ProvinceDTO dto);
	
	void deleteProvince(Long provinceId);
	
	List<ProvinceDTO> getAll();
	
	PageDTO<ProvinceDTO> getProvinces(Map<String, String> params);
	
	ProvinceDTO getProvinceByProvinceId(Long provinceId);
}
