package springboot.app.autoservice.service.impl;

import org.springframework.stereotype.Service;
import springboot.app.autoservice.dao.ProductDao;
import springboot.app.autoservice.model.Product;
import springboot.app.autoservice.service.ProductService;

import java.util.Optional;

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
