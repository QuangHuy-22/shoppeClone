package com.shoppeClone.shoppeClone.dto.orderProduct;

public class OrderProductDTO {
	
	private Long orderProductId;
	private Long  productId;
	private Long orderId;
	private Integer quantity;
	
	
	public Long getOrderProductId() {
		return orderProductId;
	}
	public void setOrderProductId(Long orderProductId) {
		this.orderProductId = orderProductId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "OrderProductDTO [orderProductId=" + orderProductId + ", productId=" + productId + ", orderId=" + orderId
				+ ", quantity=" + quantity + "]";
	}
	
	
}
