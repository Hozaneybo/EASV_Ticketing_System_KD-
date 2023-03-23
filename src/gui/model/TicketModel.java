package gui.model;

import be.*;
import bll.TicketManager;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.*;




import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TicketModel {

    private TicketManager ticketManager;


    public TicketModel(){
        ticketManager = new TicketManager();
    }


    public BufferedImage printQRCodeOnTicket(){
        return ticketManager.printQRCodeOnTicket();
    }

    public String getTicketCategory(){
        return ticketManager.getTicketCategory();
    }

    }

