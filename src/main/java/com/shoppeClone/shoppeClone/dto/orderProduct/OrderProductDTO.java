package com.shoppeClone.shoppeClone.dto.orderProduct;

import com.shoppeClone.shoppeClone.dto.product.ProductDTO;
import com.shoppeClone.shoppeClone.entity.OrderEntity;
import com.shoppeClone.shoppeClone.entity.ProductEntity;



public class OrderProductDTO {

	private Long orderProductId;
	private ProductDTO product;
	private Integer quatity;
	
	
	
	
	public ProductDTO getProduct() {
		return product;
	}
	public void setProduct(ProductDTO product) {
		this.product = product;
	}
	public Long getOrderProductId() {
		return orderProductId;
	}
	public void setOrderProductId(Long orderProductId) {
		this.orderProductId = orderProductId;
	}
	
	public Integer getQuatity() {
		return quatity;
	}
	public void setQuatity(Integer quatity) {
		this.quatity = quatity;
	}
	
	
	
	
}
