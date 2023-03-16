package autoservice.app.dto.request;

import autoservice.app.model.enums.StatusOrder;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrderRequestDto {
    private Long carsId;
    private String descriptionProblem;
    private LocalDate dateOfAcceptance;
    private List<ServiceRequestDto> services;
    private List<ProductRequestDto> products;
    private StatusOrder statusOrder;
    private BigDecimal finalCost;
    private LocalDate endDate;
    private Long carOwnerId;
    private Long masterId;

    public Long getCarsId() {
        return carsId;
    }

    public void setCarsId(Long carsId) {
        this.carsId = carsId;
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

    public List<ServiceRequestDto> getServices() {
        return services;
    }

    public void setServices(List<ServiceRequestDto> services) {
        this.services = services;
    }

    public List<ProductRequestDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductRequestDto> products) {
        this.products = products;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public BigDecimal getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(BigDecimal finalCost) {
        this.finalCost = finalCost;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Long getCarOwnerId() {
        return carOwnerId;
    }

    public void setCarOwnerId(Long carOwnerId) {
        this.carOwnerId = carOwnerId;
    }

    public Long getMasterId() {
        return masterId;
    }

    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }
}
