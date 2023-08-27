package com.shoppeClone.shoppeClone.dto.order;

import java.util.ArrayList;
import java.util.List;

import com.shoppeClone.shoppeClone.entity.UserEntity;



public class CreateOrderDTO {
	
	private Long orderId;
	
	private Long userId;
	
	private Long addressId;
	
	private List<Long> orderProductDTOs = new ArrayList<>();

	private String description;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userEntity) {
		this.userId = userEntity;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public List<Long> getOrderProductDTOs() {
		return orderProductDTOs;
	}

	public void setOrderProductDTOs(List<Long> orderProductDTOs) {
		this.orderProductDTOs = orderProductDTOs;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "CreateOrderDTO [orderId=" + orderId + ", userId=" + userId + ", addressId=" + addressId
				+ ", orderProductDTOs=" + orderProductDTOs + ", description=" + description + "]";
	}

	
	
}
