package autoservice.app.service;

import autoservice.app.model.Order;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getAllByMasterId(Long id);

    List<Order> getAllByCarOwnerId(Long id);

    Order create(Order order);

    Order update(Order order);

    BigDecimal getCostOrder(Order order);

    Optional<Order> findById(Long id);
}
