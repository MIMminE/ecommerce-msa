package my.project.msa.catalog_service.domain_model;

import lombok.*;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
public class Catalog {
    private String productId;
    private String productName;
    private Integer stock;
    private Integer unitPrice;
    private Integer totalPrice;
    private String orderId;
    private String userId;
}
