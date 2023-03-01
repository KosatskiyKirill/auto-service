package springboot.app.autoservice.service;

import springboot.app.autoservice.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getAllByMasterId(Long id);

    List<Order> getAllByCarOwnerId(Long id);

    Order create(Order order);

    Order update(Order order);

    double getCostOrder(Order order);

    Optional<Order> findById(Long id);
}
