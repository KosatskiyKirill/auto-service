package autoservice.app.service.impl;

import autoservice.app.dao.ProductDao;
import autoservice.app.model.Product;
import autoservice.app.service.ProductService;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Product create(Product product) {
        return productDao.save(product);
    }

    @Override
    public Product update(Product product) {
        return productDao.save(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productDao.findById(id);
    }
}
