package bll;

import be.*;
import dal.Ticket_DB;

import java.awt.image.BufferedImage;

public class TicketManager {

    private Ticket_DB ticketDb;

    public TicketManager(){

        ticketDb = new Ticket_DB();
    }

    public BufferedImage printQRCodeOnTicket(){
        return ticketDb.printQRCodeOnTicket();
    }

    public String getTicketCategory(){
        return ticketDb.getTicketCategory();
    }


}
