package springboot.app.autoservice.model.enums;

public enum StatusOrder {
    ACCEPTED("Accepted"),
    IN_PROCESS("In process"),
    COMPLETED_SUCCESSFULLY("Completed successfully"),
    FAILED_TO_COMPLETE("failed to complete"),
    PAID("Paid");

    private String value;

    StatusOrder(String value) {
        this.value = value;
    }
}
