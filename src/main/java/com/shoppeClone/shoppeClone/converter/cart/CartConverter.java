package com.shoppeClone.shoppeClone.converter.cart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shoppeClone.shoppeClone.dto.cart.CartDTO;
import com.shoppeClone.shoppeClone.entity.CartEntity;
import com.shoppeClone.shoppeClone.entity.ProductEntity;
import com.shoppeClone.shoppeClone.entity.UserEntity;
import com.shoppeClone.shoppeClone.respository.UserRepository;
import com.shoppeClone.shoppeClone.respository.product.ProductRepository;

import javassist.NotFoundException;

@Component
public class CartConverter {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;

    public CartDTO toDTO(CartEntity cartEntity) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartId(cartEntity.getCartId());
        cartDTO.setUserId(cartEntity.getUser().getUserId());
        cartDTO.setProductId(cartEntity.getProduct().getProductId());
        cartDTO.setQuantity(cartEntity.getQuantity());
        return cartDTO;
    }

    public CartEntity toEntity(CartDTO cartDTO) throws NotFoundException {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setQuantity(cartDTO.getQuantity());
        
		UserEntity userEntity = userRepository.findById(cartDTO.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        ProductEntity productEntity = productRepository.findById(cartDTO.getProductId())
                .orElseThrow(() -> new NotFoundException("Product not found"));

        cartEntity.setUser(userEntity);
        cartEntity.setProduct(productEntity);
        return cartEntity;
    }

    public List<CartDTO> toDTOList(List<CartEntity> cartEntities) {
        List<CartDTO> cartDTOList = new ArrayList<>();
        for (CartEntity cartEntity : cartEntities) {
            cartDTOList.add(toDTO(cartEntity));
        }
        return cartDTOList;
    }

    public List<CartEntity> toEntityList(List<CartDTO> cartDTOList) throws NotFoundException {
        List<CartEntity> cartEntities = new ArrayList<>();
        for (CartDTO cartDTO : cartDTOList) {
            cartEntities.add(toEntity(cartDTO));
        }
        return cartEntities;
    }
}

