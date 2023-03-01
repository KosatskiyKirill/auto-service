package springboot.app.autoservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.app.autoservice.model.Product;

public interface ProductDao extends JpaRepository<Product, Long> {
}
