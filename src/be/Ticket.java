package be;

public class Ticket {

    private BarEvent barEvent;
    private TicketType ticketType;
    private Customer customer;
    private QRCode qrCode;

      public Ticket(BarEvent barEvent, TicketType ticketType, Customer customer, QRCode qrCode) {
        this.barEvent = barEvent;
        this.ticketType = ticketType;
        this.customer = customer;
        this.qrCode = qrCode;

    }
    public Ticket(){

    }

    public BarEvent getBarEvent() {
        return barEvent;
    }

    public void setBarEvent(BarEvent barEvent) {
        this.barEvent = barEvent;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public QRCode getQrCode() {
        return qrCode;
    }

    public void setQrCode(QRCode qrCode) {
        this.qrCode = qrCode;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "barEvent=" + barEvent +
                ", ticketType=" + ticketType +
                ", customer=" + customer +
                ", qrCode=" + qrCode +
                '}';
    }


    public class ObjectOfTicket {
        public ObjectOfTicket(){

        }
        public TicketType createTicket(TicketType type, QRCode qrCode){
            return createTicket(type, qrCode);
        }

    }
}

