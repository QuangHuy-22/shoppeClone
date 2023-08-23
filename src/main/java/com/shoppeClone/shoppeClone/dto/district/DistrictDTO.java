package com.shoppeClone.shoppeClone.dto.district;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DistrictDTO {
    private Long districtId;
    
    private String code;
    
    private String name;
    
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date createDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date modifierDate;

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
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
}
