package autoservice.app.dao;

import autoservice.app.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaRepository<Order, Long> {
    List<Order> getAllByMasterId(Long id);

    List<Order> getAllByCarOwnerId(Long id);
}
