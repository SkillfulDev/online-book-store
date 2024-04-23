package ua.chernonog.onlinebookstore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.chernonog.onlinebookstore.config.MapperConfig;
import ua.chernonog.onlinebookstore.dto.response.shoppingcart.ShoppingCartResponseDto;
import ua.chernonog.onlinebookstore.entity.ShoppingCart;

@Mapper(config = MapperConfig.class, uses = {CartItemMapper.class})
public interface ShoppingCartMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "cartItems", target = "cartItems")
    ShoppingCartResponseDto toDto(ShoppingCart shoppingCart);

    ShoppingCart toModel(ShoppingCartResponseDto shoppingCartResponseDto);
}
