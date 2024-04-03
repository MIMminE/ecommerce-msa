package my.project.msa.order_service.service;

import my.project.msa.order_service.domain_model.Order;
import my.project.msa.order_service.persistent.jpa.OrderJpaEntity;

public interface OrderService {
    Order createOrder(Order orderDetails);

    void modifyOrder(Order orderDetails);
    Order getOrderByOrderIdAndUserId(String orderId, String userId);
    Iterable<Order> getOrdersByUserId(String userId);
}
