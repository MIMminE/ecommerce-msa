package my.project.msa.order_service.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestCreateOrder {

    public static final String PRODUCT_ID_EX_NOTNULL_MESSAGE = "productId is @NotNull";
    public static final String QTY_EX_NOTNULL_MESSAGE = "qty is @NotNull";
    public static final String UNIT_PRICE_EX_NOTNULL_MESSAGE = "unitPrice is @NotNull";

    @NotNull(message = PRODUCT_ID_EX_NOTNULL_MESSAGE)
    private String productId;

    @NotNull(message = QTY_EX_NOTNULL_MESSAGE)
    private Integer qty;

    @NotNull(message = UNIT_PRICE_EX_NOTNULL_MESSAGE)
    private Integer unitPrice;
}
