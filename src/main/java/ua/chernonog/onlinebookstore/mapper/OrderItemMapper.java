package ua.chernonog.onlinebookstore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.chernonog.onlinebookstore.config.MapperConfig;
import ua.chernonog.onlinebookstore.dto.response.orderitem.OrderItemResponseDto;
import ua.chernonog.onlinebookstore.entity.OrderItem;

@Mapper(config = MapperConfig.class)
public interface OrderItemMapper {
    @Mapping(target = "bookId", source = "book.id")
    OrderItemResponseDto toDto(OrderItem orderItem);
}
