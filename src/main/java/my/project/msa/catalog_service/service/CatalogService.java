package my.project.msa.catalog_service.service;

import my.project.msa.catalog_service.entity.CatalogEntity;

public interface CatalogService {
    Iterable<CatalogEntity> getAllCatalogs();
}
