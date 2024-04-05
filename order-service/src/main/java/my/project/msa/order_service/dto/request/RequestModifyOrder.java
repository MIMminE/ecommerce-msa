package my.project.msa.order_service.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import static my.project.msa.order_service.exception.ExceptionHolder.QTY_EX_NOTNULL_MESSAGE;

@Data
public class RequestModifyOrder {

    @NotNull(message = QTY_EX_NOTNULL_MESSAGE)
    private Integer qty;

}
