package autoservice.app.dao;

import autoservice.app.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<Order, Long> {
    List<Order> getAllByMaster_Id(Long id);

    List<Order> getAllByCarOwner_Id(Long id);

}
