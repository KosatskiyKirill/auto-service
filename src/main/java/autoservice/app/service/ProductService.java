package autoservice.app.service;


import autoservice.app.model.Product;

import java.util.Optional;

public interface ProductService {
    Product create(Product product);

    Product update(Product product);

    Optional<Product> findById(Long id);
}
