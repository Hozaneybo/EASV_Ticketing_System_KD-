package gui.controller.TicketsControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomizableTicketController implements Initializable {

    @FXML
    private Label eventName, eventAddress, eventStart, eventEnd, notes, customerName, customerEmail;

    public ImageView qr_code_image;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public Label getEventName() {
        return eventName;
    }

    public Label getEventAddress() {
        return eventAddress;
    }

    public Label getEventStart() {
        return eventStart;
    }

    public Label getEventEnd() {
        return eventEnd;
    }

    public Label getNotes() {
        return notes;
    }

    public Label getCustomerName() {
        return customerName;
    }

    public Label getCustomerEmail() {
        return customerEmail;
    }

    public ImageView getQr_code_image() {
        return qr_code_image;
    }
}