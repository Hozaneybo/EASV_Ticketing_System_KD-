package gui.controller;

import gui.model.EventCoordinatorModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EventViewController implements Initializable {

    @FXML
    private Label endTimeLbl, eventAddressLbl, eventNameLbl, eventNotes, startTimeLbl;

    private EventCoordinatorModel eventCoordinatorModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            eventCoordinatorModel = new EventCoordinatorModel();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    private void buyTickets(ActionEvent actionEvent) {
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

    public void updateEvent(ActionEvent actionEvent) {
    }
}
