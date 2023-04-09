package bll;

import be.SpecialTicket;
import dal.SpecialTicket_DB;

public class SpecialTicketManager {

    private SpecialTicket_DB specialTicketDb;

    public SpecialTicketManager(){

        specialTicketDb = new SpecialTicket_DB();
    }

    public SpecialTicket createSpecialTicket(String qrCode){
        return specialTicketDb.createSpecialTicket(qrCode);
    }
}
