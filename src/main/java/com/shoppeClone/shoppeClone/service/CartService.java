package com.shoppeClone.shoppeClone.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.shoppeClone.shoppeClone.dto.PageDTO;
import com.shoppeClone.shoppeClone.dto.cart.CartDTO;

import javassist.NotFoundException;

@Service
public interface CartService {

    CartDTO getCartById(Long cartId);

    CartDTO addToCart(CartDTO cart) throws NotFoundException;

    void deleteCart(Long cartId);
    
    PageDTO<CartDTO> getCarts(Map<String, String> params);

    
}
