package autoservice.app.service.impl;

import autoservice.app.dao.OrderDao;
import autoservice.app.model.Order;
import autoservice.app.model.Product;
import autoservice.app.model.Services;
import autoservice.app.model.enums.StatusOrder;
import autoservice.app.service.OrderService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private static final BigDecimal SERVICE_DISCOUNT = new BigDecimal(0.02);
    private static final BigDecimal PRODUCT_DISCOUNT = new BigDecimal(0.01);
    private static final BigDecimal DIAGNOSTIC = new BigDecimal(500);
    private final OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public List<Order> getAllByMasterId(Long id) {
        return orderDao.getAllByMasterId(id);
    }

    @Override
    public List<Order> getAllByCarOwnerId(Long id) {
        return orderDao.getAllByCarOwnerId(id);
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
    public BigDecimal getCostOrder(Order order) {
        BigDecimal servicesCost;
        List<Services> services = order.getServices();
        if (services.size() != 1) {
            servicesCost = services.stream()
                    .map(Services::getCost)
                    .reduce(new BigDecimal("-500.0"), BigDecimal::add);
        } else {
            servicesCost = DIAGNOSTIC;
        }
        BigDecimal productCost;
        List<Product> products = order.getProducts();
        productCost = products.stream()
                .map(Product::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        servicesCost = servicesCost.subtract(BigDecimal.valueOf(order.getCarOwner()
                .getOrders().size()));
        servicesCost = servicesCost.multiply(SERVICE_DISCOUNT);

        productCost = productCost.subtract(productCost.equals(new BigDecimal("0.0"))
                ? new BigDecimal("0")
                : BigDecimal.valueOf(order.getCarOwner().getOrders().size()));
        productCost = productCost.multiply(PRODUCT_DISCOUNT);

        order.setFinalCost(servicesCost.add(productCost));
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
