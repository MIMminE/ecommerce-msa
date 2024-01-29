package my.project.msa.my_msa_order_service.service;

import my.project.msa.my_msa_order_service.dto.OrderDto;
import my.project.msa.my_msa_order_service.jpa.OrderEntity;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDetails);
    OrderDto getOrderBtOrderId(String orderId);
    Iterable<OrderEntity> getOrdersByUserId(String userId);
}
