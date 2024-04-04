package my.project.msa.catalog_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ResponseCatalog {
    private String productId;
    private String productName;
    private Integer unitPrice;
    private Integer qty;
    private Date createdAt;
}
