package be;
    // Java code to generate QR code


import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;


    public class QRCodeOnly {

        String uniqueID = UUID.randomUUID().toString();


        // Function to create the QR code
        public static void createQR(String data, String path, String charset, int height, int width) throws WriterException, IOException {

            BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, width, height);

            MatrixToImageWriter.writeToPath(matrix, path.substring(path.lastIndexOf('.') + 1), Paths.get(path));

            // The data that the QR code will contain
            data = "ya khalaf khara 3lk";

            // The path where the image will get saved
            path = "/image/QR-code.png";

            // Encoding charset
            charset = "UTF-8";

            // Create the QR code and save in the specified folder

            createQR(data, path, charset, 200, 200);
        }

        // Driver code
        public static void main(String[] args) throws WriterException, IOException, NotFoundException {

          // createQR("Ali", "src/image/QR-Code.png", "UTF-8", 200, 200);

            System.out.println("QR Code Generated!!! ");
        }
    }


