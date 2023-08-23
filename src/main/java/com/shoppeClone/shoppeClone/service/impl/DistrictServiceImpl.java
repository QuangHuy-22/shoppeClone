package com.shoppeClone.shoppeClone.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppeClone.shoppeClone.converter.district.DistrictConverter;
import com.shoppeClone.shoppeClone.dto.PageDTO;
import com.shoppeClone.shoppeClone.dto.district.DistrictDTO;
import com.shoppeClone.shoppeClone.entity.DistrictEntity;
import com.shoppeClone.shoppeClone.exception.ValidateException;
import com.shoppeClone.shoppeClone.respository.district.DistrictRepository;
import com.shoppeClone.shoppeClone.service.DistrictService;
import com.shoppeClone.shoppeClone.utils.AppStringUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private DistrictConverter districtConverter;

    @Autowired
    private EntityManager entityManager;

    @Override
    public DistrictDTO createDistrict(DistrictDTO dto) {
        // Validate dữ liệu
        String name = dto.getName();
        if (!AppStringUtils.hasText(name)) {
            throw new ValidateException("District name cannot be empty");
        }
        // DTO -> Entity
        DistrictEntity newDistrictEntity = districtConverter.toEntity(dto);
        districtRepository.save(newDistrictEntity);

        // Entity -> Dto
        DistrictDTO resultDto = districtConverter.toDTO(newDistrictEntity);

        return resultDto;
    }

    @Override
    public DistrictDTO updateDistrict(Long districtId, DistrictDTO districtDTO) {
        DistrictEntity districtEntity = districtRepository.findById(districtId)
                .orElseThrow(() -> new ValidateException("District not found"));
        districtConverter.toEntity(districtEntity, districtDTO);
        districtRepository.save(districtEntity);
        return districtConverter.toDTO(districtEntity);
    }

    @Override
    public void deleteDistrict(Long districtId) {
		districtRepository.findById(districtId).orElseThrow(() -> new ValidateException("District not found"));
		districtRepository.deleteById(districtId);
    }

    @Override
    public List<DistrictDTO> getAll() {
        List<DistrictEntity> districtEntities = districtRepository.findAll();
        List<DistrictDTO> districtDTOs = districtConverter.toDTOList(districtEntities);
        return districtDTOs;
    }

    @Override
    public PageDTO<DistrictDTO> getDistricts(Map<String, String> params) {
    	// http://localhost:8080/admin/api/v1/district?page=1&limit=10
		System.out.println(params);
		String pageStr = params.get("page");
		String limitStr = params.get("limit");
		Integer page = 1;
		Integer limit = 10;
		if (AppStringUtils.hasText(pageStr)) {
			page = Integer.valueOf(pageStr);
		}
		if (AppStringUtils.hasText(limitStr)) {
			
			limit = Integer.valueOf(limitStr);
		}
		StringBuilder selectQueryBuilder = new StringBuilder("SELECT c FROM DistrictEntity c");
		StringBuilder countQueryBuilder 
				= new StringBuilder("SELECT COUNT(c.districtId) FROM DistrictEntity c");
		
		String name = params.get("name");
		if (AppStringUtils.hasText(name)) {
			selectQueryBuilder.append(" WHERE c.name LIKE :name" );
			countQueryBuilder.append(" WHERE c.name LIKE :name");
		}
	
		TypedQuery<DistrictEntity> selectQuery 
				= entityManager.createQuery(selectQueryBuilder.toString(), DistrictEntity.class);
		
		TypedQuery<Long> countQuery 
			= entityManager.createQuery(countQueryBuilder.toString(), Long.class);
		Integer firstItems = (page - 1) * limit;
		
		if (AppStringUtils.hasText(name)) {
			selectQuery.setParameter("name", "%" + name + "%");
			countQuery.setParameter("name", "%" + name + "%");
		}
		
		selectQuery.setFirstResult(firstItems);
		selectQuery.setMaxResults(limit);
		
		List<DistrictEntity> districtEntities = selectQuery.getResultList();
		Long totalItems = countQuery.getSingleResult();
		
		// entity -> dto
		List<DistrictDTO> dtos = districtConverter.toDTOList(districtEntities);
		
		return new PageDTO<>(page, limit, totalItems, dtos);
    }

    @Override
    public DistrictDTO getDistrictByDistrictId(Long districtId) {
        DistrictEntity districtEntity = districtRepository.findById(districtId)
                .orElseThrow(() -> new ValidateException("Không tìm thấy Id của district"));
        return districtConverter.toDTO(districtEntity);
    }
}

