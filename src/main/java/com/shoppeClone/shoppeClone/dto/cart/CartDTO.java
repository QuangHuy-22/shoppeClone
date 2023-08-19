package com.shoppeClone.shoppeClone.dto.cart;

public class CartDTO {

    private Long cartId;
    
    private Long userId;
    
    private Long productId;
    
    private Integer quantity;
    
    public CartDTO() {
    
    }

    public CartDTO(Long cartId, Long userId, Long productId, Integer quantity) {
        this.cartId = cartId;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

	
	
}
