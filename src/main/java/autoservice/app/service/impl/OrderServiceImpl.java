package autoservice.app.service.impl;

import autoservice.app.model.enums.StatusOrder;
import autoservice.app.service.OrderService;
import autoservice.app.dao.OrderDao;
import autoservice.app.model.Order;
import autoservice.app.model.Product;
import autoservice.app.model.Services;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public List<Order> getAllByMasterId(Long id) {
        return orderDao.getAllByMaster_Id(id);
    }

    @Override
    public List<Order> getAllByCarOwnerId(Long id) {
        return orderDao.getAllByCarOwner_Id(id);
    }

    @Override
    public Order create(Order order) {
        return orderDao.save(order);
    }

    @Override
    public Order update(Order order) {
        return orderDao.save(order);
    }

    @Override
    public double getCostOrder(Order order) {
        double servicesCost = 0;
        List<Services> services = order.getServices();
        if (services.size() != 1) {
            servicesCost = services.stream()
                    .mapToDouble(Services::getCost)
                    .sum() - 500.0;
        } else {
            servicesCost = 500.0;
        }
        double productCost = order.getProducts().stream()
                .mapToDouble(Product::getCost)
                .sum();
        servicesCost -= order.getCarOwner().getOrders().size() * 0.02;
        productCost -= productCost == 0.0 ? 0 : order.getCarOwner().getOrders().size() * 0.01;
        order.setFinalCost(servicesCost + productCost);
        order.setStatusOrder(StatusOrder.COMPLETED_SUCCESSFULLY);
        order.setEndDate(LocalDate.now());
        update(order);
        return order.getFinalCost();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderDao.findById(id);
    }
}
