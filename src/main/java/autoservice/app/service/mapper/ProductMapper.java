package autoservice.app.service.mapper;

import autoservice.app.dto.request.ProductRequestDto;
import autoservice.app.dto.response.ProductResponseDto;
import autoservice.app.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductResponseDto toDto(Product product) {
        ProductResponseDto productDto = new ProductResponseDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setCost(product.getCost());
        return productDto;
    }

    public Product toModel(ProductRequestDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setCost(productDto.getCost());
        return product;
    }
}
