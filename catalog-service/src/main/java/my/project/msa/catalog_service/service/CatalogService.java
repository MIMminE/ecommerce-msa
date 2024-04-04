package my.project.msa.catalog_service.service;

import my.project.msa.catalog_service.domain_model.Catalog;

public interface CatalogService {
    Iterable<Catalog> getAllCatalogs();
}
