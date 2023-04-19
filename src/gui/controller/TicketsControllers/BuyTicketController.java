package gui.controller.TicketsControllers;

import be.TicketType;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import gui.model.FacadeModel;
import gui.model.FacadeModelLoader;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
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
    private ComboBox < String > ticketType;
    private FacadeModel facadeModel;
    private FacadeModelLoader facadeModelLoader;
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
        facadeModelLoader = FacadeModelLoader.getInstance();
        facadeModel = facadeModelLoader.getFacadeModel();
        ticketType.setItems(facadeModel.getEventCoordinatorModel().getTypes());
    }
    @FXML
    void submitBuying(ActionEvent event) {
        String name = customerNameField.getText().trim();
        String email = customerEmailField.getText().trim();
        String ticket = ticketType.getValue();
        String quantityStr = ticketQuantity.getText().trim();
        int quantity;

        // validate customerNameField
        if (name.isEmpty() || name.length() > 20 || !name.matches("^[a-zA-Z ]+$")) {
            facadeModel.getAlert("Invalid Input", null, "Please enter a valid name (up to 20 letters and spaces only).", Alert.AlertType.WARNING);
            return;
        }

        // validate customerEmailField
        if (email.isEmpty() || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            facadeModel.getAlert("Invalid Input", null, "Please enter a valid email address.", Alert.AlertType.WARNING);
            return;
        }

        // check if ticket type is selected
        if (ticket == null) {
            facadeModel.getAlert("Invalid Input", null, "Please select a ticket type.", Alert.AlertType.WARNING);
            return;
        }

        // validate ticketQuantity

        try {
            quantity = Integer.parseInt(quantityStr);
            if (quantity < 1 || quantity > 10) {
                facadeModel.getAlert("Invalid Input", null, "Please enter a valid ticket quantity (1-10 only).", Alert.AlertType.WARNING);
                return;
            }
        } catch (NumberFormatException e) {
            facadeModel.getAlert("Invalid Input", null, "Please enter a valid ticket quantity (1-50 only).", Alert.AlertType.WARNING);
            return;
        }

        try {
            facadeModel.getCustomerModel().createCustomer(name, email);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        buyMoreThanOneTicket();
        saveTicketsAsPDF();

        // List<Node> tickets = new ArrayList<>();
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void buyMoreThanOneTicket() {
        int quantity = Integer.parseInt(ticketQuantity.getText());
        for (int j = 0; j < quantity; j++) {
            showTicketsCategory();
            putTicketInDataBase();
        }
    }
    private void showTicketsCategory() {
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
                controller.getCustomerEmailLabel().setText(customerEmailField.getText());
                BufferedImage qrCodeImage = facadeModel.getTicketModel().printQRCodeOnTicket();
                controller.getQr_code_image().setImage(SwingFXUtils.toFXImage(qrCodeImage, null));
                Stage stage = new Stage();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Standard Ticket");
                stage.setResizable(false);
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
                BufferedImage qrCodeImage = facadeModel.getTicketModel().printQRCodeOnTicket();
                controller.getQr_code_image().setImage(SwingFXUtils.toFXImage(qrCodeImage, null));
                Stage stage = new Stage();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Customize Ticket");
                stage.setResizable(false);
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
                stage.setResizable(false);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void saveTicketsAsPDF() {

        int quantity = Integer.parseInt(ticketQuantity.getText());
        String fileName = "ticketsToPrint/tickets.pdf";

        File file = new File(fileName);
        int num = 1;
        while (file.exists()) {
            fileName = "ticketsToPrint/tickets" + "(" + num + ")" + ".pdf";
            file = new File(fileName);
            num++;
        }

        try (FileOutputStream out = new FileOutputStream(file); PdfWriter writer = new PdfWriter(out); PdfDocument pdfDoc = new PdfDocument(writer); Document document = new Document(pdfDoc)) {

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