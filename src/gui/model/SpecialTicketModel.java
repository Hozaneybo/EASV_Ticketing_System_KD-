package gui.model;

import be.SpecialTicket;
import bll.SpecialTicketManager;

public class SpecialTicketModel {

    private SpecialTicketManager specialTicketManager;

    public SpecialTicketModel() {
        specialTicketManager = new SpecialTicketManager();
    }

    public SpecialTicket createSpecialTicket(String qrCode) {
        return specialTicketManager.createSpecialTicket(qrCode);
    }
}