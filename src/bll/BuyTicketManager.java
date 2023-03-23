package bll;

import be.BarEvent;
import be.Customer;
import be.Ticket;
import be.TicketType;
import dal.Ticket_DB;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class BuyTicketManager {

    private Ticket_DB ticketDb;

    public BuyTicketManager() {
        ticketDb = new Ticket_DB();
    }

    public Ticket buyTicket(BarEvent barEvent, TicketType ticketType, Customer customer) {
        String qrCodeData = generateTicketData(barEvent, ticketType, customer);
        String qrCode = generateQRCode(qrCodeData);
        Ticket ticket = new Ticket(barEvent, ticketType, customer, qrCode);
        ticketDb.saveTicket(ticket);
        BufferedImage printedTicket = ticketDb.printQRCodeOnTicket(qrCode);
        // display or send printedTicket to customer's email
        return ticket;
    }

    public ArrayList<Ticket> buyMultipleTickets(BarEvent barEvent, TicketType ticketType, Customer customer, int quantity) {
        ArrayList<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            Ticket ticket = buyTicket(barEvent, ticketType, customer);
            tickets.add(ticket);
        }
        return tickets;
    }

    private String generateTicketData(BarEvent barEvent, TicketType ticketType, Customer customer) {
        String data = "event:" + barEvent.getId() + ",ticketType:" + ticketType.getId() + ",customer:" + customer.getId();
        return data;
    }

    private String generateQRCode(String data) {
        int qrCodeSize = 200;
        BufferedImage qrCodeImage = ticketDb.generateQRCodeImage(data, qrCodeSize);
        // save qrCodeImage to file or convert to string
        return "qrCodeDataHere"; // return qrCode data as string for demo purposes
    }
}
