package my.project.msa.catalog_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Data
@Entity
@Table(name="catalog")
public class CatalogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, length = 120, unique = true)
    private String productId;

    @Column(nullable = false)
    private String ProductName;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private Integer unitPrice;

    @Column(nullable = false, updatable = false, insertable = false) // * Notion 기록용
    @ColumnDefault(value = "CURRENT_TIMESTAMP") // * Notion 기록용
    private Date createdAt;
}
