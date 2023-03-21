package be;



import java.util.UUID;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

    public class QRCode {

        public static void main(String[] args) {

            // Generate a unique ID
            String uniqueID = UUID.randomUUID().toString();

            // Create QR code content with the unique ID
            String qrCodeContent = "Drasing Kabab group welcomes you " + uniqueID;

            // Set QR code properties
            int width = 150;
            int height = 150;
            String format = "png";
            Path path = FileSystems.getDefault().getPath("src/image/QR-code.png");

            // Generate QR code image
            try {
                QRCodeWriter qrCodeWriter = new QRCodeWriter();
                BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeContent, BarcodeFormat.QR_CODE, width, height, getQRCodeHints());
                MatrixToImageWriter.writeToPath(bitMatrix, format, path);
            } catch (WriterException | IOException e) {
                e.printStackTrace();
            }

            System.out.println("QR code with unique ID " + uniqueID + " has been generated and saved to " + path);
        }

        private static java.util.Map<EncodeHintType, Object> getQRCodeHints() {
            java.util.Map<EncodeHintType, Object> hints = new java.util.HashMap<>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.MARGIN, 1);
            return hints;
        }
    }


