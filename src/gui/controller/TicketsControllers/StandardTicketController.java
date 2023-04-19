package gui.controller.TicketsControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class StandardTicketController implements Initializable {

    @FXML
    private Label customerName, eventAddress, eventEnd, eventName, eventStart, notes, customerEmailLabel;
    @FXML
    private ImageView qr_code_image;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public Label getCustomerName() {
        return customerName;
    }

    public Label getEventAddress() {
        return eventAddress;
    }

    public Label getEventEnd() {
        return eventEnd;
    }

    public Label getEventName() {
        return eventName;
    }

    public Label getEventStart() {
        return eventStart;
    }

    public Label getNotes() {
        return notes;
    }

    public ImageView getQr_code_image() {
        return qr_code_image;
    }

    public Label getCustomerEmailLabel() {
        return customerEmailLabel;
    }
}