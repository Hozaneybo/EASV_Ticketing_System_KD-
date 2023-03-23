package gui.controller.costumerControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerEventViewController implements Initializable {


    @FXML
    private Label endTimeLbl, eventAddressLbl, eventNameLbl, eventNotes, startTimeLbl;




    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void buyTickets(ActionEvent actionEvent) {
    }

    public Label getEndTimeLbl() {
        return endTimeLbl;
    }

    public Label getEventAddressLbl() {
        return eventAddressLbl;
    }

    public Label getEventNameLbl() {
        return eventNameLbl;
    }

    public Label getEventNotes() {
        return eventNotes;
    }

    public Label getStartTimeLbl() {
        return startTimeLbl;
    }

    public void deleteEvent(ActionEvent actionEvent) {


    }
}
