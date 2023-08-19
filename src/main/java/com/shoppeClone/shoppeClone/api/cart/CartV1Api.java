package com.shoppeClone.shoppeClone.api.cart;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppeClone.shoppeClone.dto.PageDTO;
import com.shoppeClone.shoppeClone.dto.cart.CartDTO;
import com.shoppeClone.shoppeClone.service.CartService;

import javassist.NotFoundException;

@RestController
@RequestMapping("admin/api/v1/carts")
public class CartV1Api {
	
	@Autowired
	private CartService cartService;
	
	@GetMapping
	public PageDTO<CartDTO> getCarts(
			@RequestParam Map<String , String> params) {
		return cartService.getCarts(params);
	}

	@PostMapping
    public CartDTO addToCart(@RequestBody CartDTO cartDTO) throws NotFoundException {
        return cartService.addToCart(cartDTO);
    }

    @DeleteMapping("{cartId}")
    public void deleteCart(@PathVariable(value = "cartId")Long cartId) {
        cartService.deleteCart(cartId);
    }

    
    
    
}
