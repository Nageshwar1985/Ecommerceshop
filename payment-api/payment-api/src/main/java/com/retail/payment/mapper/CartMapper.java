package com.retail.payment.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.retail.payment.dto.CartDTO;
import com.retail.payment.model.Cart;


@Mapper(componentModel = "spring")
public interface CartMapper {

    static CartMapper getInstance() {
        return Mappers.getMapper(CartMapper.class);
    }

    Cart fromDto(CartDTO dto);

    
    @Mappings({
        @Mapping(target="cartId", source="entity.id"),
      })    
    CartDTO toDto(Cart entity);

//    Authority fromDto(AuthorityDto dto);
//
//    AuthorityDto toDto(GrantedAuthority entity);
}
