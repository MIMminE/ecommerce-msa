package my.project.msa.order_service.persistent.jpa;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderJpaEntity, Long> {
    OrderJpaEntity findByOrderId(String orderId);
    Iterable<OrderJpaEntity> findByUserId(String userId);
    OrderJpaEntity findByOrderIdAndAndUserId(String orderId, String userId);

    @Modifying
    @Query("update OrderJpaEntity o Set o.qty = :qty, o.totalPrice = :totalPrice where o.orderId = :orderId and o.userId = :userId")
    int updateOrder(Integer qty, Integer totalPrice, String orderId, String userId);
}
