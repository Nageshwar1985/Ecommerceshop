package com.retail.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.retail.order.dto.OrderDTO;
import com.retail.order.model.Order;


@Mapper(componentModel = "spring")
public interface OrderMapper {

    static OrderMapper getInstance() {
        return Mappers.getMapper(OrderMapper.class);
    }

    Order fromDto(OrderDTO dto);
    
 
    OrderDTO toDto(Order entity);

//    Authority fromDto(AuthorityDto dto);
//
//    AuthorityDto toDto(GrantedAuthority entity);
}
