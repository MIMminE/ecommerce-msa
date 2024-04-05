package my.project.msa.order_service.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.project.msa.order_service.domain_model.Order;
import my.project.msa.order_service.dto.request.RequestCreateOrder;
import my.project.msa.order_service.dto.request.RequestModifyOrder;
import my.project.msa.order_service.dto.response.ResponseOrder;
import my.project.msa.order_service.mapper.OrderDomainMapper;
import my.project.msa.order_service.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/order-service")
public class OrderController {

    private final OrderService orderService;
    private final OrderDomainMapper orderMapper = OrderDomainMapper.INSTANCE;

    @GetMapping("/health_check")
    public String status(HttpServletRequest request) {
        return String.format("It's Working in User Service %s", request.getServerPort());
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder(@PathVariable String userId,
                                                     @RequestBody @Valid RequestCreateOrder requestCreateOrder) {
        Order order = orderMapper.fromRequestCreateOrder(requestCreateOrder);
        order.setUserId(userId);
        Order createdOrder = orderService.createOrder(order);

        ResponseOrder responseOrder = orderMapper.toResponseOrder(createdOrder);
        return ResponseEntity.ok(responseOrder);
    }

    @PostMapping("/{userId}/orders/{orderId}/update")
    public ResponseEntity<?> modifyOrder(@PathVariable String userId,
                                                     @PathVariable String orderId,
                                                     @RequestBody @Valid RequestModifyOrder requestModifyOrder) {

        Order order = orderMapper.fromRequestModifyOrder(requestModifyOrder);
        setModifyOrderValues(userId, orderId, order);
        orderService.modifyOrder(order);

        return ResponseEntity.ok().build();
    }


    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrder(@PathVariable("userId") String userId) {
        Iterable<Order> ordersByUserId = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(
                StreamSupport.stream(ordersByUserId.spliterator(), false)
                        .map(orderMapper::toResponseOrder)
                        .collect(Collectors.toList())
        );
    }

    private void setModifyOrderValues(String userId, String orderId, Order order) {
        order.setOrderId(orderId);
        order.setUserId(userId);
    }
}
