package my.project.msa.order_service.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import static my.project.msa.order_service.exception.ExceptionHolder.*;

@Data
public class RequestCreateOrder {

    @NotNull(message = PRODUCT_ID_EX_NOTNULL_MESSAGE)
    private String productId;

    @NotNull(message = QTY_EX_NOTNULL_MESSAGE)
    private Integer qty;

    @NotNull(message = UNIT_PRICE_EX_NOTNULL_MESSAGE)
    private Integer unitPrice;
}
