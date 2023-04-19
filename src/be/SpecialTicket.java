package be;

public class SpecialTicket {

    private int id;
    private String qr_code;
    private boolean valid;

    public SpecialTicket(int id, String qr_code, boolean valid) {
        this.id = id;
        this.qr_code = qr_code;
        this.valid = true;
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

    public boolean getStatus() {
        return valid;
    }

    public void setStatus(boolean used) {
        this.valid = used;
    }
}