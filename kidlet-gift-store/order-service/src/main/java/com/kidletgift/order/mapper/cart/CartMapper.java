package com.kidletgift.order.mapper.cart;

import com.kidletgift.order.controller.cartrestservice.request.AddToCartRequest;
import com.kidletgift.order.dto.AddToCartDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CartMapper {

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    /**
     * Get AddToCartDTO from the AddToCartRequest
     * @param addToCartRequest
     * @return
     */
    @Mapping(target = "cartItemDTO.",source = ".")
    AddToCartDTO addToCartRequestToAddToCartDTO(AddToCartRequest addToCartRequest);
}
