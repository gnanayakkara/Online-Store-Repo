package com.kidletgift.order.mapper.order;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public class OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);


}
