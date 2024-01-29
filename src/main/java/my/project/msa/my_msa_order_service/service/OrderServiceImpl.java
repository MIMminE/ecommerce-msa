package my.project.msa.my_msa_order_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.project.msa.my_msa_order_service.dto.OrderDto;
import my.project.msa.my_msa_order_service.jpa.OrderEntity;
import my.project.msa.my_msa_order_service.jpa.OrderRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private ModelMapper mapper = new ModelMapper();

    @Override
    public OrderDto createOrder(OrderDto orderDetails) {
        orderDetails.setOrderId(UUID.randomUUID().toString());
        orderDetails.setTotalPrice(orderDetails.getQty() * orderDetails.getUnitPrice());
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        OrderEntity mapped = mapper.map(orderDetails, OrderEntity.class);
        log.info("order entity : {}",mapped);
        orderRepository.save(mapped);

        OrderDto map = mapper.map(mapped, OrderDto.class);
        log.info("order dto : {}",map);
        return map;
    }

    @Override
    public OrderDto getOrderByOrderId(String orderId) {
        OrderEntity byOrderId = orderRepository.findByOrderId(orderId);
        return mapper.map(byOrderId, OrderDto.class);
    }

    @Override
    public Iterable<OrderEntity> getOrdersByUserId(String userId) {
        Iterable<OrderEntity> byUserId = orderRepository.findByUserId(userId);
        List<OrderEntity> result = new ArrayList<>();

        byUserId.forEach(result::add);
        return byUserId;
    }
}
