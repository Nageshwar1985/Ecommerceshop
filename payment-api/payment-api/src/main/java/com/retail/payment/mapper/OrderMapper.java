package com.retail.payment.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.retail.payment.dto.OrderDTO;
import com.retail.payment.model.Order;


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
