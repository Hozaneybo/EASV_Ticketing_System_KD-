package dal;


import be.Ticket;
import be.TicketType;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import dal.database.DBConnector;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.UUID;


public class Ticket_DB {

    private Ticket ticket;
    private DBConnector dbConnector;
    TicketType ticketType;

    public Ticket_DB() {
        dbConnector = new DBConnector();
        ticket = new Ticket();

    }

      public BufferedImage printQRCodeOnTicket() {

        // Generate QR code image
        String qrCodeData = "ticket_data_here"; // Replace with the unique ticket data
        int qrCodeSize = 200; // Set the size of the QR code image
        BufferedImage qrCodeImage = generateQRCodeImage(qrCodeData, qrCodeSize);

        // Open ticket template image
        File ticketFile = new File("src\\image\\QR-code.png"); // Replace with the path to the ticket template image
        BufferedImage ticketImage = null;
        try {
            ticketImage = ImageIO.read(ticketFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Paste QR code onto ticket image
        int x = 200;
        int y = 200;
        Graphics2D graphics = ticketImage.createGraphics();
        graphics.drawImage(qrCodeImage, x, y, null);

        // Save modified ticket image as a file
        File printedTicketFile = new File("printed_ticket.png"); // Replace with the desired file format and name
        try {
            ImageIO.write(ticketImage, "png", printedTicketFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ticketImage;
    }

    public BufferedImage generateQRCodeImage(String qrCodeData, int size) {

        String uniqueID = UUID.randomUUID().toString();

        // Create QR code content with the unique ID
        String qrCodeContent = "https://www.easv.dk/da/uddannelser  " + uniqueID;

        // Set QR code properties
        int width = 200;
        int height = 200;
        String format = "png";

        Path path = FileSystems.getDefault().getPath("src/image/QR-code.png");

        // Generate QR code image
        BitMatrix bitMatrix = null;
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            bitMatrix = qrCodeWriter.encode(qrCodeContent, BarcodeFormat.QR_CODE, width, height, getQRCodeHints());
            MatrixToImageWriter.writeToPath(bitMatrix, format, path);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    private static java.util.Map<EncodeHintType, Object> getQRCodeHints() {
        java.util.Map<EncodeHintType, Object> hints = new java.util.HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 1);
        return hints;
    }

    public String getTicketCategory() {
        if (this.ticketType == TicketType.STANDARD) {
            return "Standard Ticket";
        } else if (this.ticketType == TicketType.CUSTOMIZED) {
            return "Customized Ticket";
        } else if (this.ticketType == TicketType.SPECIAL) {
            return "Special Ticket";
        } else {
            return "Unknown Ticket Type";
        }
    }



}




