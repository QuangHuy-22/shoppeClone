package com.shoppeClone.shoppeClone.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="wards")
public class WardEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long wardId;
	
	@Column(unique = true)
	private String code;
	
	private String name;

	public Long getProvinceId() {
		return wardId;
	}

	public void setProvinceId(Long provinceId) {
		this.wardId = provinceId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}
