package autoservice.app.model.enums;

public enum StatusService {
    PAID("Paid"),
    NOT_PAID("Not paid");
    private String value;

    StatusService(String value) {
        this.value = value;
    }
}
