package com.shoppeClone.shoppeClone.dto.order;

import java.util.List;

import com.shoppeClone.shoppeClone.dto.orderProduct.OrderProductDTO;

public class OrderDTO {

		private Long orderId;
	    private Long userId;
	    private Long addressId;
	    private String description;
	    private List<OrderProductDTO> orderProducts;
	    
		public Long getOrderId() {
			return orderId;
		}
		public void setOrderId(Long orderId) {
			this.orderId = orderId;
		}
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
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public List<OrderProductDTO> getOrderProducts() {
			return orderProducts;
		}
		public void setOrderProducts(List<OrderProductDTO> orderProducts) {
			this.orderProducts = orderProducts;
		}

	    // Getters and setters
	    
	    
	
}
