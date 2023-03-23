
package be;

import java.util.UUID;

public class Ticket {

    private BarEvent barEvent;
    private TicketType ticketType;
    private Customer customer;
    private String qrCode;


      public Ticket(BarEvent barEvent, TicketType ticketType, Customer customer, String qrCode) {

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

    public TicketType getTicketType() {
        return ticketType;
    }


    public Customer getCustomer() {
        return customer;
    }


    public String getQrCode() {
        return qrCode;
    }




}

