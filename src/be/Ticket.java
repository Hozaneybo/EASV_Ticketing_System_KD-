package be;

public class Ticket {

    private int id;
    private int event_id;
    private int customer_id;
    private int coordinator_id;
    private String qrCode;

    public Ticket(int id, int event_id, int customer_id, int coordinator_id, String qrCode) {
        this.id = id;
        this.event_id = event_id;
        this.customer_id = customer_id;
        this.coordinator_id = coordinator_id;
        this.qrCode = qrCode;
    }

    public Ticket() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getCoordinator_id() {
        return coordinator_id;
    }

    public void setCoordinator_id(int coordinator_id) {
        this.coordinator_id = coordinator_id;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

}