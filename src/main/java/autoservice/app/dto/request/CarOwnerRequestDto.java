package autoservice.app.dto.request;

import java.util.List;

public class CarOwnerRequestDto {
    private List<CarRequestDto> cars;
    private List<OrderRequestDto> orders;

    public List<CarRequestDto> getCars() {
        return cars;
    }

    public void setCars(List<CarRequestDto> cars) {
        this.cars = cars;
    }

    public List<OrderRequestDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderRequestDto> orders) {
        this.orders = orders;
    }
}
