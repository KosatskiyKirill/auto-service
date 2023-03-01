package springboot.app.autoservice.dto;

import springboot.app.autoservice.model.enums.StatusOrder;

import java.time.LocalDate;
import java.util.List;

public class OrderDto {
    private Long id;
    private CarDto cars;
    private String descriptionProblem;
    private LocalDate dateOfAcceptance;
    private List<ServiceDto> services;
    private List<ProductDto> products;
    private StatusOrder statusOrder;
    private double finalCost;
    private LocalDate endDate;
    private CarOwnerDto carOwner;
    private MasterDto master;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarDto getCarsDto() {
        return cars;
    }

    public void setCarsDto(CarDto cars) {
        this.cars = cars;
    }

    public String getDescriptionProblem() {
        return descriptionProblem;
    }

    public void setDescriptionProblem(String descriptionProblem) {
        this.descriptionProblem = descriptionProblem;
    }

    public LocalDate getDateOfAcceptance() {
        return dateOfAcceptance;
    }

    public void setDateOfAcceptance(LocalDate dateOfAcceptance) {
        this.dateOfAcceptance = dateOfAcceptance;
    }

    public List<ServiceDto> getServices() {
        return services;
    }

    public void setServicesDto(List<ServiceDto> services) {
        this.services = services;
    }

    public List<ProductDto> getProductsDto() {
        return products;
    }

    public void setProductsDto(List<ProductDto> products) {
        this.products = products;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public double getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(double finalCost) {
        this.finalCost = finalCost;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public CarOwnerDto getCarOwnerDto() {
        return carOwner;
    }

    public void setCarOwnerDto(CarOwnerDto carOwner) {
        this.carOwner = carOwner;
    }

    public MasterDto getMasterDto() {
        return master;
    }

    public void setMasterDto(MasterDto master) {
        this.master = master;
    }
}
