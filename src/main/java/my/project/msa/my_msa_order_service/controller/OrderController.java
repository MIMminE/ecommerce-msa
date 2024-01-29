package my.project.msa.my_msa_order_service.controller;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.project.msa.my_msa_order_service.dto.OrderDto;
import my.project.msa.my_msa_order_service.jpa.OrderEntity;
import my.project.msa.my_msa_order_service.response.ResponseOrder;
import my.project.msa.my_msa_order_service.service.OrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/order-service")
public class OrderController {
    private final OrderService orderService;
    private ModelMapper mapper = new ModelMapper();

    @PostMapping("/orders")
    public ResponseEntity<ResponseOrder> createOrder(@RequestBody OrderDto orderDto){
        OrderDto orderDetails = orderService.createOrder(orderDto);

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ResponseOrder mapped = mapper.map(orderDetails, ResponseOrder.class);
        return ResponseEntity.ok(mapped);
    }
}
