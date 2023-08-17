package com.shoppeClone.shoppeClone.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppeClone.shoppeClone.converter.supplier.SupplierConverter;
import com.shoppeClone.shoppeClone.dto.PageDTO;
import com.shoppeClone.shoppeClone.dto.supplier.SupplierDTO;
import com.shoppeClone.shoppeClone.entity.SupplierEntity;
import com.shoppeClone.shoppeClone.exception.ValidateException;
import com.shoppeClone.shoppeClone.respository.supplier.SupplierRepository;
import com.shoppeClone.shoppeClone.service.SupplierService;
import com.shoppeClone.shoppeClone.utils.AppStringUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private SupplierConverter supplierConverter;

    @Autowired
    private EntityManager entityManager;

    @Override
    public SupplierDTO createSupplier(SupplierDTO dto) {
        // Validate dữ liệu
        String name = dto.getName();
        if (!AppStringUtils.hasText(name)) {
            throw new ValidateException("Supplier name can not empty");
        }
        // Dto -> entity
        SupplierEntity newSupplierEntity = supplierConverter.toEntity(dto);
        supplierRepository.save(newSupplierEntity);
        // entity -> dto
        SupplierDTO resultDto = supplierConverter.toDTO(newSupplierEntity);
        return resultDto;
    }

    @Override
    public PageDTO<SupplierDTO> getSuppliers(Map<String, String> params) {
    	// http://localhost:8080/admin/api/v1/supplier?page=1&limit=10
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
		// lấy dữ liệu
		// đếm dữ liệu
		StringBuilder selectQueryBuilder = new StringBuilder("SELECT c FROM SupplierEntity c");
		StringBuilder countQueryBuilder 
				= new StringBuilder("SELECT COUNT(c.supplierId) FROM SupplierEntity c");
		
		String name = params.get("name");
		if (AppStringUtils.hasText(name)) {
			selectQueryBuilder.append(" WHERE c.name LIKE :name" );
			countQueryBuilder.append(" WHERE c.name LIKE :name");
		}
	
		TypedQuery<SupplierEntity> selectQuery 
				= entityManager.createQuery(selectQueryBuilder.toString(), SupplierEntity.class);
		
		TypedQuery<Long> countQuery 
			= entityManager.createQuery(countQueryBuilder.toString(), Long.class);
		Integer firstItems = (page - 1) * limit;
		
		if (AppStringUtils.hasText(name)) {
			selectQuery.setParameter("name", "%" + name + "%");
			countQuery.setParameter("name", "%" + name + "%");
		}
		
		selectQuery.setFirstResult(firstItems);
		selectQuery.setMaxResults(limit);
		
		List<SupplierEntity> supplierEntities = selectQuery.getResultList();
		Long totalItems = countQuery.getSingleResult();
		
		// entity -> dto
		List<SupplierDTO> dtos = supplierConverter.toDTOList(supplierEntities);
		
		return new PageDTO<>(page, limit, totalItems, dtos);
    }

    @Override
    public SupplierDTO updateSupplier(Long supplierId, SupplierDTO supplierDTO) {
        SupplierEntity supplierEntity = supplierRepository
                .findById(supplierId).orElseThrow(() -> new ValidateException("Supplier not found"));

        supplierConverter.toEntity(supplierEntity, supplierDTO);
        supplierRepository.save(supplierEntity);

        return supplierConverter.toDTO(supplierEntity);
    }

    @Override
    public void deleteSupplier(Long supplierId) {
        supplierRepository
                .findById(supplierId).orElseThrow(() -> new ValidateException("Supplier not found"));
        supplierRepository.deleteById(supplierId);
    }

    @Override
    public SupplierDTO getSupplierById(Long supplierId) {
        SupplierEntity supplierEntity = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new ValidateException("Không tìm thấy nhà cung cấp"));

        return supplierConverter.toDTO(supplierEntity);
    }


}
