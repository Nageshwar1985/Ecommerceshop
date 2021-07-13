package com.retail.payment.mapper;

import com.retail.payment.dto.OrderDTO;
import com.retail.payment.model.Order;
import com.retail.payment.model.OrderProduct;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-13T17:45:54+0530",
    comments = "version: 1.2.0.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.2 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order fromDto(OrderDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Order order = new Order();

        order.setCartId( dto.getCartId() );
        order.setCreatedOn( dto.getCreatedOn() );
        order.setId( dto.getId() );
        List<OrderProduct> list = dto.getOrderProducts();
        if ( list != null ) {
            order.setOrderProducts( new ArrayList<OrderProduct>( list ) );
        }
        else {
            order.setOrderProducts( null );
        }
        order.setStatus( dto.getStatus() );
        order.setTotalAmount( dto.getTotalAmount() );
        order.setUserId( dto.getUserId() );

        return order;
    }

    @Override
    public OrderDTO toDto(Order entity) {
        if ( entity == null ) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setCartId( entity.getCartId() );
        orderDTO.setCreatedOn( entity.getCreatedOn() );
        orderDTO.setId( entity.getId() );
        List<OrderProduct> list = entity.getOrderProducts();
        if ( list != null ) {
            orderDTO.setOrderProducts( new ArrayList<OrderProduct>( list ) );
        }
        else {
            orderDTO.setOrderProducts( null );
        }
        orderDTO.setStatus( entity.getStatus() );
        orderDTO.setTotalAmount( entity.getTotalAmount() );
        orderDTO.setUserId( entity.getUserId() );

        return orderDTO;
    }
}
