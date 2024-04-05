package my.project.msa.catalog_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.project.msa.catalog_service.domain_model.Catalog;
import my.project.msa.catalog_service.mapper.CatalogMapper;
import my.project.msa.catalog_service.repository.jpa.CatalogJpaEntity;
import my.project.msa.catalog_service.repository.jpa.CatalogRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService{
    private final CatalogRepository catalogRepository;
    private final CatalogMapper catalogMapper = Mappers.getMapper(CatalogMapper.class);

    @Override
    public Iterable<Catalog> getAllCatalogs() {
        Iterable<CatalogJpaEntity> entities = catalogRepository.findAll();
        return StreamSupport.stream(entities.spliterator(), false)
                .map(catalogMapper::fromCatalogJpaEntity)
                .collect(Collectors.toList());
    }
}
