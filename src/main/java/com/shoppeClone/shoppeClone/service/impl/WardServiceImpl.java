package com.shoppeClone.shoppeClone.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppeClone.shoppeClone.converter.ward.WardConverter;
import com.shoppeClone.shoppeClone.dto.PageDTO;
import com.shoppeClone.shoppeClone.dto.ward.WardDTO;
import com.shoppeClone.shoppeClone.entity.WardEntity;
import com.shoppeClone.shoppeClone.exception.ValidateException;
import com.shoppeClone.shoppeClone.respository.ward.WardRepository;
import com.shoppeClone.shoppeClone.service.WardService;
import com.shoppeClone.shoppeClone.utils.AppStringUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class WardServiceImpl implements WardService {

    @Autowired
    private WardRepository wardRepository;

    @Autowired
    private WardConverter wardConverter;

    @Autowired
    private EntityManager entityManager;

    @Override
    public WardDTO createWard(WardDTO dto) {
        // Validate dữ liệu
        String name = dto.getName();
        if (!AppStringUtils.hasText(name)) {
            throw new ValidateException("Ward name cannot be empty");
        }
        // DTO -> Entity
        WardEntity newWardEntity = wardConverter.toEntity(dto);
        wardRepository.save(newWardEntity);

        // Entity -> DTO
        WardDTO resultDto = wardConverter.toDTO(newWardEntity);

        return resultDto;
    }

    @Override
    public WardDTO updateWard(Long wardId, WardDTO wardDTO) {
        WardEntity wardEntity = wardRepository.findById(wardId)
                .orElseThrow(() -> new ValidateException("Ward not found"));
        wardConverter.toEntity(wardEntity, wardDTO);
        wardRepository.save(wardEntity);
        return wardConverter.toDTO(wardEntity);
    }

    @Override
    public void deleteWard(Long wardId) {
        wardRepository.findById(wardId).orElseThrow(() -> new ValidateException("Ward not found"));
        wardRepository.deleteById(wardId);
    }

    @Override
    public List<WardDTO> getAll() {
        List<WardEntity> wardEntities = wardRepository.findAll();
        List<WardDTO> wardDTOs = wardConverter.toDTOList(wardEntities);
        return wardDTOs;
    }

    @Override
    public PageDTO<WardDTO> getWards(Map<String, String> params) {
    	// http://localhost:8080/admin/api/v1/ward?page=1&limit=10
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
    			StringBuilder selectQueryBuilder = new StringBuilder("SELECT c FROM WardEntity c");
    			StringBuilder countQueryBuilder 
    					= new StringBuilder("SELECT COUNT(c.wardId) FROM WardEntity c");
    			
    			String name = params.get("name");
    			if (AppStringUtils.hasText(name)) {
    				selectQueryBuilder.append(" WHERE c.name LIKE :name" );
    				countQueryBuilder.append(" WHERE c.name LIKE :name");
    			}
    		
    			TypedQuery<WardEntity> selectQuery 
    					= entityManager.createQuery(selectQueryBuilder.toString(), WardEntity.class);
    			
    			TypedQuery<Long> countQuery 
    				= entityManager.createQuery(countQueryBuilder.toString(), Long.class);
    			Integer firstItems = (page - 1) * limit;
    			
    			if (AppStringUtils.hasText(name)) {
    				selectQuery.setParameter("name", "%" + name + "%");
    				countQuery.setParameter("name", "%" + name + "%");
    			}
    			
    			selectQuery.setFirstResult(firstItems);
    			selectQuery.setMaxResults(limit);
    			
    			List<WardEntity> wardEntities = selectQuery.getResultList();
    			Long totalItems = countQuery.getSingleResult();
    			
    			
				// entity -> dto
    			List<WardDTO> dtos = wardConverter.toDTOList(wardEntities);
    			
    			return new PageDTO<>(page, limit, totalItems, dtos);
    }

    @Override
    public WardDTO getWardByWardId(Long wardId) {
        WardEntity wardEntity = wardRepository.findById(wardId)
                .orElseThrow(() -> new ValidateException("Không tìm thấy Id của ward"));
        return wardConverter.toDTO(wardEntity);
    }
}
