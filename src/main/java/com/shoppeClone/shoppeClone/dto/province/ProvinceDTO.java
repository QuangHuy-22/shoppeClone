package com.shoppeClone.shoppeClone.dto.province;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ProvinceDTO {

	private Long provinceId;
	
	private String code;
	
	private String name;
	
	@JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
	private Date createDate;
	
	@JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
	private Date modifierDate;
	
	private String createBy;
	
	private String modifierBy;

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifierDate() {
		return modifierDate;
	}

	public void setModifierDate(Date modifierDate) {
		this.modifierDate = modifierDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getModifierBy() {
		return modifierBy;
	}

	public void setModifierBy(String modifierBy) {
		this.modifierBy = modifierBy;
	}
	
	
}
