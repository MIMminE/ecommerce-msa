package my.project.msa.my_msa_order_service.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestOrder {

    @NotNull
    private String productId;

    @NotNull
    private Integer qty;

    @NotNull
    private Integer unitPrice;
}
