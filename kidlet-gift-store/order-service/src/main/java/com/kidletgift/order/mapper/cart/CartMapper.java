package com.kidletgift.order.mapper.cart;

import com.kidletgift.order.controller.cartrestservice.request.CartRequest;
import com.kidletgift.order.controller.cartrestservice.response.CartGiftItems;
import com.kidletgift.order.dto.CartDTO;
import com.kidletgift.order.dto.CartItemDTO;
import com.kidletgift.order.model.orderdoc.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CartMapper {

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    /**
     * Get AddToCartDTO from the AddToCartRequest
     * @param cartRequest
     * @return
     */
    @Mapping(target = "cartItemDTO.",source = ".")
    CartDTO cartRequestToCartDTO(CartRequest cartRequest);

    /**
     * Get CartItem from DTO for Model Mapping
     * @param cartItemDTO
     * @return
     */
    CartItem cartItemDTOToCartItem(CartItemDTO cartItemDTO);

    /**
     * Get CartItemDTO from CartItem
     * @param cartItem
     * @return
     */
    CartItemDTO cartItemToCartItemDTO(CartItem cartItem);

    /**
     * Get CartGiftItems from CartItemDTO
     * @return
     */
    CartGiftItems cartItemDTOToCartGiftItems(CartItemDTO cartItemDTO);

}
