package my.project.msa.catalog_service.mapper;

import my.project.msa.catalog_service.domain_model.Catalog;
import my.project.msa.catalog_service.dto.ResponseCatalog;
import my.project.msa.catalog_service.repository.jpa.CatalogJpaEntity;
import org.mapstruct.Mapper;

@Mapper
public interface CatalogMapper {
    ResponseCatalog toResponseCatalog(Catalog catalog);

    Catalog fromCatalogJpaEntity(CatalogJpaEntity catalogJpaEntity);
}
