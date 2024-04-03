package my.project.msa.order_service.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestModifyOrder {

    public static final String QTY_EX_NOTNULL_MESSAGE = "QTY_EX_NOTNULL_MESSAGE";

    @NotNull(message = QTY_EX_NOTNULL_MESSAGE)
    private Integer qty;

}
