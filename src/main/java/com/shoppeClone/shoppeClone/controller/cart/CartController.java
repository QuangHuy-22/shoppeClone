package com.shoppeClone.shoppeClone.controller.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppeClone.shoppeClone.dto.cart.CartDTO;
import com.shoppeClone.shoppeClone.service.CartService;

@Controller
@RequestMapping("admin/carts")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@GetMapping("list")
	public String showCartList() {
		return "admin/cart/list-cart";
	}
	
	@GetMapping("{cartId}")
    public String viewCartDetail(@PathVariable Long cartId, Model model) {
        CartDTO cartDTO = cartService.getCartById(cartId);
        model.addAttribute("cart", cartDTO);
        return "admin/cart/detail-cart"; 
    }
	
	@GetMapping("add")
    public String addToCart() {
        return "admin/cart/add-to-cart"; 
    }

    @GetMapping("{cartId}/delete")
    public String deleteCart(@PathVariable Long cartId) {
        return "admin/cart/delete-cart";
    }


	
}
