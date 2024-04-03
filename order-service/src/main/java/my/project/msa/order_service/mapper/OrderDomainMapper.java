package my.project.msa.order_service.mapper;

import my.project.msa.order_service.domain_model.Order;
import my.project.msa.order_service.dto.request.RequestCreateOrder;
import my.project.msa.order_service.dto.request.RequestModifyOrder;
import my.project.msa.order_service.dto.response.ResponseOrder;
import my.project.msa.order_service.persistent.jpa.OrderJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderDomainMapper {
    OrderDomainMapper INSTANCE = Mappers.getMapper(OrderDomainMapper.class);

    Order fromRequestCreateOrder(RequestCreateOrder source);

    OrderJpaEntity toOrderJpaEntity(Order source);

    Order fromJpaEntity(OrderJpaEntity source);

    ResponseOrder toResponseOrder(Order source);

    Order fromRequestModifyOrder(RequestModifyOrder source);
}
