package autoservice.app.dto.mapper.impl;

import autoservice.app.dto.ProductDto;
import autoservice.app.model.Product;
import autoservice.app.dto.mapper.DtoMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements DtoMapper<ProductDto, Product> {
    @Override
    public ProductDto toDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setCost(product.getCost());
        return dto;
    }

    @Override
    public Product toModel(ProductDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setCost(dto.getCost());
        return product;
    }
}
