package my.project.msa.order_service.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrderDto implements Serializable {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private Date createAt;

    private String orderId;
    private String userId;
}
