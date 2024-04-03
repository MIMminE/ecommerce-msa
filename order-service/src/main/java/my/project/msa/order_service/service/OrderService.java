package my.project.msa.order_service.service;

import my.project.msa.order_service.dto.OrderDto;
import my.project.msa.order_service.jpa.OrderEntity;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDetails);
    OrderDto getOrderByOrderId(String orderId);
    Iterable<OrderEntity> getOrdersByUserId(String userId);
}
