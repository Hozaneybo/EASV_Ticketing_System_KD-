package gui.model;

import be.*;
import bll.QrCodeManager;
import bll.TicketManager;


import java.awt.image.BufferedImage;

public class TicketModel {

    private TicketManager ticketManager;
    private QrCodeManager qrCodeManager;



    public TicketModel(){
        ticketManager = new TicketManager();
        qrCodeManager = new QrCodeManager();
    }


    public BufferedImage printQRCodeOnTicket(){
        return qrCodeManager.printQRCodeOnTicket();
    }


    public  String readQRCodeFromFile(){
       return qrCodeManager.readQRCodeFromFile();
    }

    public Ticket createTicket(int event, int customer, int coordinator, String qrCode) throws Exception {
        return ticketManager.createTicket(event, customer, coordinator, qrCode);
    }

}

