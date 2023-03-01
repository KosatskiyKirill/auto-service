package autoservice.app.controller;

import autoservice.app.dto.ProductDto;
import autoservice.app.dto.mapper.impl.ProductMapper;
import autoservice.app.model.Product;
import autoservice.app.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        Product newProduct = productService.create(productMapper.toModel(productDto));
        return ResponseEntity.ok(productMapper.toDto(newProduct));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long productId,
                                                    @RequestBody ProductDto productDto) {
        return productService.findById(productId)
                .map(p -> {
                    p.setName(productDto.getName());
                    p.setCost(productDto.getCost());
                    return productService.update(p);
                })
                .map(productMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }
}
