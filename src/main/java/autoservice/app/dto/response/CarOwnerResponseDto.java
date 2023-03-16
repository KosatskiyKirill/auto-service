package autoservice.app.dto.response;

import java.util.List;

public class CarOwnerResponseDto {
    private Long id;
    private List<CarResponseDto> cars;
    private List<OrderResponseDto> orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CarResponseDto> getCars() {
        return cars;
    }

    public void setCars(List<CarResponseDto> cars) {
        this.cars = cars;
    }

    public List<OrderResponseDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderResponseDto> orders) {
        this.orders = orders;
    }
}
