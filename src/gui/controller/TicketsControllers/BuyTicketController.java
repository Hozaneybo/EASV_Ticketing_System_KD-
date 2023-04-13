package gui.controller.TicketsControllers;

import be.TicketType;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import gui.model.FacadeModel;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BuyTicketController implements Initializable {
    @FXML
    private TextField customerNameField, ticketQuantity, customerEmailField;
    @FXML
    private Label eventLocationLbl, eventNameLbl;
    @FXML
    private ComboBox < String > ticketType;
    private FacadeModel facadeModel;
    private int eventIndex;
    private Scene scene;
    public void setEventIndex(int eventIndex) {
        this.eventIndex = eventIndex;
    }
    public int getEventIndex() {
        return eventIndex;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            facadeModel = new FacadeModel();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ticketType.setItems(facadeModel.getEventCoordinatorModel().getTypes());
    }
    @FXML
    void submitBuying(ActionEvent event) throws IOException {
        String name = customerNameField.getText();
        String email = customerEmailField.getText();
        if (!name.isEmpty() && !email.isEmpty()) {
            try {
                facadeModel.getCustomerModel().createCustomer(name, email);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        buyMoreThanOneTicket();
        saveTicketsAsPDF();

        // List<Node> tickets = new ArrayList<>();
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    public void buyMoreThanOneTicket() throws IOException {
        int quantity = Integer.parseInt(ticketQuantity.getText());
        for ( int j = 0; j < quantity; j++) {
            showTicketsCategory();
            putTicketInDataBase();
        }
    }
    private void showTicketsCategory() throws IOException {
        if (ticketType.getValue().equals(TicketType.STANDARD.toString())) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/Tickets/StandardTicket.fxml"));
                Parent root = loader.load();
                StandardTicketController controller = loader.getController();
                int eventsIndex = getEventIndex();
                controller.getEventName().setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(eventsIndex).getEventName());
                controller.getEventAddress().setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(eventsIndex).getEventAddress());
                controller.getNotes().setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(eventsIndex).getNotes());
                controller.getEventStart().setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(eventsIndex).getStartTime());
                controller.getEventEnd().setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(eventsIndex).getEndTime());
                controller.getCustomerName().setText(customerNameField.getText());
                eventNameLbl.setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(eventsIndex).getEventName());
                eventLocationLbl.setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(eventsIndex).getEventAddress());
                BufferedImage qrCodeImage = facadeModel.getTicketModel().printQRCodeOnTicket();
                controller.getQr_code_image().setImage(SwingFXUtils.toFXImage(qrCodeImage, null));
                Stage stage = new Stage();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Standard Ticket");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ticketType.getValue().equals(TicketType.CUSTOMIZED.toString())) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/Tickets/CustomizableTicket.fxml"));
                Parent root = loader.load();
                CustomizableTicketController controller = loader.getController();
                int eventsIndex = getEventIndex();
                controller.getEventName().setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(eventsIndex).getEventName());
                controller.getEventAddress().setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(eventsIndex).getEventAddress());
                controller.getNotes().setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(eventsIndex).getNotes());
                controller.getEventStart().setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(eventsIndex).getStartTime());
                controller.getEventEnd().setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(eventsIndex).getEndTime());
                controller.getCustomerName().setText(customerNameField.getText());
                controller.getCustomerEmail().setText(customerEmailField.getText());
                eventNameLbl.setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(eventsIndex).getEventName());
                eventLocationLbl.setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(eventsIndex).getEventAddress());
                BufferedImage qrCodeImage = facadeModel.getTicketModel().printQRCodeOnTicket();
                controller.getQr_code_image().setImage(SwingFXUtils.toFXImage(qrCodeImage, null));
                Stage stage = new Stage();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Customize Ticket");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ticketType.getValue().equals(TicketType.SPECIAL.toString())) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/Tickets/SpecialTicket.fxml"));
                Parent root = loader.load();
                SpecialTicketController controller = loader.getController();
                BufferedImage qrCodeImage = facadeModel.getTicketModel().printQRCodeOnTicket();
                controller.getQr_code().setImage(SwingFXUtils.toFXImage(qrCodeImage, null));
                String qrcode = facadeModel.getTicketModel().readQRCodeFromFile();
                facadeModel.getSpecialTicketModel().createSpecialTicket(qrcode);
                Stage stage = new Stage();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Special Ticket");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void saveTicketsAsPDF() throws IOException {

        int quantity = Integer.parseInt(ticketQuantity.getText());

        File file = new File("ticketsToPrint/tickets.pdf");

        try (FileOutputStream out = new FileOutputStream(file);
             PdfWriter writer = new PdfWriter(out);
             PdfDocument pdfDoc = new PdfDocument(writer);
             Document document = new Document(pdfDoc)) {

            for (int i = 0; i < quantity; i++) {
                WritableImage snapshot = scene.snapshot(null);
                ImageData imageData = ImageDataFactory.create(SwingFXUtils.fromFXImage(snapshot, null), null);
                Image image = new Image(imageData);
                document.add(image);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* public void printTickets() {

         if (receiptMethod.getValue().equals("Print")) {
             try {
                 // Load the PDF document
                 PDDocument document = PDDocument.load(new File("ticketsToPrint/tickets.pdf"));

                 // Create a print job
                 PrinterJob job = PrinterJob.getPrinterJob();
                 job.setPageable(new PDFPageable(document));

                 // Show the print dialog
                 if (job.printDialog()) {
                     // Print the document
                     job.print();
                 }

                 // Close the PDF document
                 document.close();

             } catch (IOException | PrinterException e) {
                 e.printStackTrace();
             }
         }
     }*/
    public void putTicketInDataBase() {
        int eventsIndex = getEventIndex();
        try {
            int event_id = facadeModel.getEventCoordinatorModel().getObservableEvents().get(eventsIndex).getId();
            int coordinator_id = facadeModel.getEventCoordinatorModel().getCoordinatorId(event_id);
            String qr_code = facadeModel.getTicketModel().readQRCodeFromFile();
            facadeModel.getCustomerModel().refreshCustomer();
            int customer_id = facadeModel.getCustomerModel().getObservableCustomers().get(facadeModel.getCustomerModel().getObservableCustomers().size() - 1).getId();
            facadeModel.getTicketModel().createTicket(event_id, customer_id, coordinator_id, qr_code);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}