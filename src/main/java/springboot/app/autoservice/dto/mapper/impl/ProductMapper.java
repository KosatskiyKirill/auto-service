package springboot.app.autoservice.dto.mapper.impl;

import org.springframework.stereotype.Component;
import springboot.app.autoservice.dto.ProductDto;
import springboot.app.autoservice.dto.mapper.DtoMapper;
import springboot.app.autoservice.model.Product;

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
