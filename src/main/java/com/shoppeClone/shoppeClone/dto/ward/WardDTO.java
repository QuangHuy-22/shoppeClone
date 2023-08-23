package com.shoppeClone.shoppeClone.dto.ward;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class WardDTO {
	
    private Long wardId;
    
    private String code;
    
    private String name;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date createDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date modifierDate;

    public Long getWardId() {
        return wardId;
    }

    public void setWardId(Long wardId) {
        this.wardId = wardId;
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
