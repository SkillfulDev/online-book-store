package ua.chernonog.onlinebookstore.mapper;

import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ua.chernonog.onlinebookstore.config.MapperConfig;
import ua.chernonog.onlinebookstore.dto.response.order.OrderResponseDto;
import ua.chernonog.onlinebookstore.entity.Order;

@Mapper(config = MapperConfig.class, uses = OrderItemMapper.class)
public interface OrderMapper {
    @Mapping(source = "user.id", target = "userId")
    OrderResponseDto toDto(Order order);

    @AfterMapping
    default void mapOrderItemsToDto(
            Order order,
            @MappingTarget OrderResponseDto dto,
            OrderItemMapper orderItemMapper) {
        if (order.getOrderItems() != null) {
            dto.setOrderItems(order.getOrderItems().stream()
                    .map(orderItemMapper::toDto)
                    .collect(Collectors.toSet()));
        }
    }
}
