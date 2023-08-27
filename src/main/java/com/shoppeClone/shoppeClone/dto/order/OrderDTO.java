package com.shoppeClone.shoppeClone.dto.order;

import java.util.ArrayList;
import java.util.List;

import com.shoppeClone.shoppeClone.dto.address.AddressDTO;
import com.shoppeClone.shoppeClone.dto.orderProduct.OrderProductDTO;
import com.shoppeClone.shoppeClone.dto.user.UserDTO;


public class OrderDTO {

	private Long orderId;
	
	private Long userId;
	
	private Long addressId;
	
	private List<OrderProductDTO> orderProductDTOs = new ArrayList<>();

	private String description;
	
	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public List<OrderProductDTO> getOrderProductDTOs() {
		return orderProductDTOs;
	}

	public void setOrderProductDTOs(List<OrderProductDTO> orderProductDTOs) {
		this.orderProductDTOs = orderProductDTOs;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "OrderDTO [orderId=" + orderId + ", userId=" + userId + ", addressId=" + addressId
				+ ", orderProductDTOs=" + orderProductDTOs + ", description=" + description + "]";
	}
	
	
}
