package be;

public class SpecialTicket {

    private int id;
    private String qr_code;
    private boolean used;

    public SpecialTicket(int id, String qr_code, boolean used) {
        this.id = id;
        this.qr_code = qr_code;
        this.used = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQr_code() {
        return qr_code;
    }

    public void setQr_code(String qr_code) {
        this.qr_code = qr_code;
    }

    public boolean isStatus() {
        return used;
    }

    public void setStatus(boolean used) {
        this.used = used;
    }
}
