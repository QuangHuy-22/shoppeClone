package com.shoppeClone.shoppeClone.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppeClone.shoppeClone.converter.province.ProvinceConverter;
import com.shoppeClone.shoppeClone.dto.PageDTO;
import com.shoppeClone.shoppeClone.dto.province.ProvinceDTO;
import com.shoppeClone.shoppeClone.entity.ProvinceEntity;
import com.shoppeClone.shoppeClone.exception.ValidateException;
import com.shoppeClone.shoppeClone.respository.pronvice.ProvinceRepository;
import com.shoppeClone.shoppeClone.service.ProvinceService;
import com.shoppeClone.shoppeClone.utils.AppStringUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProvinceServiceImpl implements ProvinceService {

	@Autowired
	private ProvinceRepository provinceRepository;

	@Autowired
	private ProvinceConverter provinceConverter;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public ProvinceDTO createProvince(ProvinceDTO dto) {
		// Validate dữ liệu
		String name = dto.getName();
		if (!AppStringUtils.hasText(name)) {
			throw new ValidateException("Province name can not emty");
		}
		// DTO -> Entity
		ProvinceEntity newProvinceEntity = provinceConverter.toEntity(dto);
		provinceRepository.save(newProvinceEntity);

		// Entity -> Dto
		ProvinceDTO resulDto = provinceConverter.toDTO(newProvinceEntity);

		return resulDto;
	}

	@Override
	public ProvinceDTO updateProvince(Long provinceId, ProvinceDTO provinceDTO) {
		ProvinceEntity provinceEntity = provinceRepository.findById(provinceId)
				.orElseThrow(() -> new ValidateException("Province not found"));
		provinceConverter.toEntity(provinceEntity, provinceDTO);
		provinceRepository.save(provinceEntity);
		return provinceConverter.toDTO(provinceEntity);
	}

	@Override
	public void deleteProvince(Long provinceId) {
		ProvinceEntity provinceEntity = provinceRepository.findById(provinceId)
				.orElseThrow(() -> new ValidateException("Province not Id"));
		provinceRepository.deleteById(provinceId);

	}

	@Override
	public List<ProvinceDTO> getAll() {
		List<ProvinceEntity> provinceEntities = provinceRepository.findAll();
		List<ProvinceDTO> provinceDTOs = provinceConverter.toDtoList(provinceEntities);
		return provinceDTOs;
	}

	@Override
	public PageDTO<ProvinceDTO> getProvinces(Map<String, String> params) {
		// HQL
		System.out.println(params);
		String pagestr = params.get("page");
		String limitstr = params.get("limit");
		Integer page = 1;
		Integer limit = 10;

		if (AppStringUtils.hasText(pagestr)) {
			page = Integer.valueOf(pagestr);
		}
		if (AppStringUtils.hasText(limitstr)) {
			limit = Integer.valueOf(limitstr);
		}
//		Lay du lieu
		StringBuilder selectQueryBuilder = new StringBuilder("select p from ProvinceEntity p");
		
		StringBuilder countQeryBuilder = 
				new StringBuilder("SELECT COUNT(p.provinceId) FROM ProvinceEntity p");
		
		String name = params.get("name");
		
		if (AppStringUtils.hasText(name)) {
			selectQueryBuilder.append(" where p.name like :name");
			countQeryBuilder.append(" where p.name like :name");
		}
		
		TypedQuery<ProvinceEntity> selectQuery = 
				entityManager.createQuery(selectQueryBuilder.toString(), ProvinceEntity.class);
		
		TypedQuery<Long> countQuery =
				entityManager.createQuery(countQeryBuilder.toString(), Long.class);
		
		Integer firsItems = (page -1) * limit;
		
		if (AppStringUtils.hasText(name)) {
			selectQuery.setParameter("name", "%" + name + "%");
			countQuery.setParameter("name", "%" + name + "%");
		}
		selectQuery.setFirstResult(firsItems);
		selectQuery.setMaxResults(limit);
		
		List<ProvinceEntity> provinceEntities = selectQuery.getResultList();
		Long totalItems = countQuery.getSingleResult();
		
		// dto -> entity
		List<ProvinceDTO> dtos = provinceConverter.toDtoList(provinceEntities);
			
		
		
		return new PageDTO<>(page, limit, totalItems, dtos);
	}

	@Override
	public ProvinceDTO getProvinceByProvinceId(Long provinceId) {
		// Tìm kiếm id của province
		ProvinceEntity provinceEntity = provinceRepository.findById(provinceId)
				.orElseThrow(() -> new ValidateException("Không tìm thấy Id của province"));
		return provinceConverter.toDTO(provinceEntity);
	}

}
