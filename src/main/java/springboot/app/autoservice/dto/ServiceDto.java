package springboot.app.autoservice.dto;

import org.springframework.stereotype.Component;
import springboot.app.autoservice.model.enums.StatusService;

@Component
public class ServiceDto {
    private Long id;
    private OrderDto order;
    private MasterDto master;
    private double cost;
    private StatusService status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderDto getOrderDto() {
        return order;
    }

    public void setOrderDto(OrderDto order) {
        this.order = order;
    }

    public MasterDto getMasterDto() {
        return master;
    }

    public void setMasterDto(MasterDto master) {
        this.master = master;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public StatusService getStatus() {
        return status;
    }

    public void setStatus(StatusService status) {
        this.status = status;
    }
}
