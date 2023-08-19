package com.shoppeClone.shoppeClone.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="carts")
public class CartEntity extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartId;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable = false)
	private UserEntity user;
	
	@ManyToOne
	@JoinColumn(name="product_id", nullable = false)
	private ProductEntity product;
	
	private Integer quantity;
	
	private Long userId;
	
	private Long productId;

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	public Long getUserId() {
		return userId;
	}

	public Long getProductId() {
		return productId;
	}

	
	
	
}
