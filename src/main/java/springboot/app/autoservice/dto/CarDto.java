package springboot.app.autoservice.dto;

public class CarDto {
    private Long id;
    private String brand;
    private String model;
    private String carNumber;
    private CarOwnerDto carOwnerDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public CarOwnerDto getCarOwnerDto() {
        return carOwnerDto;
    }

    public void setCarOwnerDto(CarOwnerDto carOwnerDto) {
        this.carOwnerDto = carOwnerDto;
    }
}
