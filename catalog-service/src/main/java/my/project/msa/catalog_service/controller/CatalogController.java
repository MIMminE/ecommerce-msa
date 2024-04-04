package my.project.msa.catalog_service.controller;

import lombok.RequiredArgsConstructor;
import my.project.msa.catalog_service.domain_model.Catalog;
import my.project.msa.catalog_service.dto.ResponseCatalog;
import my.project.msa.catalog_service.mapper.CatalogMapper;
import my.project.msa.catalog_service.service.CatalogService;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/catalog-service")
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;
    private final CatalogMapper catalogMapper = Mappers.getMapper(CatalogMapper.class);

    @GetMapping("/catalogs")
    public ResponseEntity<List<ResponseCatalog>> getCatalogs() {

        Iterable<Catalog> catalogs = catalogService.getAllCatalogs();
        System.out.println(catalogs);

        return ResponseEntity.status(HttpStatus.OK).body(
                StreamSupport.stream(catalogs.spliterator(), false)
                        .map(catalogMapper::toResponseCatalog)
                        .collect(Collectors.toList())
        );
    }
}
