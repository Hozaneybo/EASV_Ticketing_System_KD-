package bll;

import be.*;
import dal.Ticket_DB;


public class TicketManager {

    private Ticket_DB ticketDb;

    public TicketManager(){

        ticketDb = new Ticket_DB();
    }

    public Ticket createTicket(int event, int customer, int coordinator, String qrCode) throws Exception {
        return  ticketDb.createTicket(event, customer, coordinator, qrCode);
    }


}
