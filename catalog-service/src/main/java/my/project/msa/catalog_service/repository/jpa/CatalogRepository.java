package my.project.msa.catalog_service.repository.jpa;

import org.springframework.data.repository.CrudRepository;

public interface CatalogRepository extends CrudRepository<CatalogJpaEntity, Long> {
    CatalogJpaEntity findByProductId(String productId);
}
