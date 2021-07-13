package com.retail.payment.mapper;

import com.retail.payment.dto.CartDTO;
import com.retail.payment.dto.ProductDTO;
import com.retail.payment.model.Cart;
import com.retail.payment.model.Product;
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
public class CartMapperImpl implements CartMapper {

    @Override
    public Cart fromDto(CartDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Cart cart = new Cart();

        cart.setProducts( productDTOListToProductList( dto.getProducts() ) );
        cart.setStatus( dto.getStatus() );

        return cart;
    }

    @Override
    public CartDTO toDto(Cart entity) {
        if ( entity == null ) {
            return null;
        }

        CartDTO cartDTO = new CartDTO();

        cartDTO.setCartId( entity.getId() );
        cartDTO.setProducts( productListToProductDTOList( entity.getProducts() ) );
        cartDTO.setStatus( entity.getStatus() );

        return cartDTO;
    }

    protected Product productDTOToProduct(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setCategory( productDTO.getCategory() );
        product.setDesc( productDTO.getDesc() );
        product.setId( productDTO.getId() );
        product.setName( productDTO.getName() );
        product.setQuantity( productDTO.getQuantity() );
        product.setStatus( productDTO.getStatus() );

        return product;
    }

    protected List<Product> productDTOListToProductList(List<ProductDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Product> list1 = new ArrayList<Product>( list.size() );
        for ( ProductDTO productDTO : list ) {
            list1.add( productDTOToProduct( productDTO ) );
        }

        return list1;
    }

    protected ProductDTO productToProductDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setCategory( product.getCategory() );
        productDTO.setDesc( product.getDesc() );
        productDTO.setId( product.getId() );
        productDTO.setName( product.getName() );
        productDTO.setQuantity( product.getQuantity() );
        productDTO.setStatus( product.getStatus() );

        return productDTO;
    }

    protected List<ProductDTO> productListToProductDTOList(List<Product> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductDTO> list1 = new ArrayList<ProductDTO>( list.size() );
        for ( Product product : list ) {
            list1.add( productToProductDTO( product ) );
        }

        return list1;
    }
}
