package my.project.msa.order_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.project.msa.order_service.domain_model.Order;
import my.project.msa.order_service.mapper.OrderDomainMapper;
import my.project.msa.order_service.persistent.jpa.OrderJpaEntity;
import my.project.msa.order_service.persistent.jpa.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderDomainMapper orderMapper = OrderDomainMapper.INSTANCE;

    @Override
    @Transactional
    public Order createOrder(Order order) {
        setOrderValues(order);
        OrderJpaEntity orderJpaEntity = orderMapper.toOrderJpaEntity(order);
        OrderJpaEntity saved = orderRepository.save(orderJpaEntity);
        return orderMapper.fromJpaEntity(saved);
    }

    @Override
    @Transactional
    public void modifyOrder(Order orderDetails) {
        OrderJpaEntity jpaEntity = orderRepository.findByOrderIdAndAndUserId(orderDetails.getOrderId(), orderDetails.getUserId());
        Integer totalPrice = orderDetails.getQty() * jpaEntity.getUnitPrice();

        int modifyCount = orderRepository.updateOrder(
                orderDetails.getQty(),
                totalPrice,
                orderDetails.getOrderId(),
                orderDetails.getUserId());

    }

    @Override
    public Order getOrderByOrderIdAndUserId(String orderId, String userId) {
        OrderJpaEntity orderJpaEntity = orderRepository.findByOrderIdAndAndUserId(orderId, userId);

        return orderMapper.fromJpaEntity(orderJpaEntity);
    }

    @Override
    public Iterable<Order> getOrdersByUserId(String userId) {
        Iterable<OrderJpaEntity> orderJpaEntities = orderRepository.findByUserId(userId);
        return StreamSupport.stream(orderJpaEntities.spliterator(), false)
                .map(orderMapper::fromJpaEntity)
                .collect(Collectors.toList());
    }

    private void setOrderValues(Order order) {
        order.setOrderId(UUID.randomUUID().toString());
        order.setTotalPrice(order.getQty() * order.getUnitPrice());
    }
}
