package bll;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.UUID;

public class QrCodeManager {

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
        String qrCodeContent =  uniqueID;

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

    public static java.util.Map<EncodeHintType, Object> getQRCodeHints() {
        java.util.Map<EncodeHintType, Object> hints = new java.util.HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 1);
        return hints;
    }

    public String readQRCodeFromFile() {
        String result = "";

        // Open QR code image file
        File qrCodeFile = new File("src/image/QR-code.png"); // Replace with the path to the QR code image file
        BufferedImage qrCodeImage = null;
        try {
            qrCodeImage = ImageIO.read(qrCodeFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Read QR code from image
        QRCodeReader qrCodeReader = new QRCodeReader();
        LuminanceSource source = new BufferedImageLuminanceSource(qrCodeImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        try {
            Result qrCodeResult = qrCodeReader.decode(bitmap);
            result = qrCodeResult.getText();
        } catch (NotFoundException | ChecksumException | FormatException e) {
            e.printStackTrace();
        }

        return result;
    }

}
