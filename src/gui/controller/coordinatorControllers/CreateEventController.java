package gui.controller.coordinatorControllers;

import gui.model.EventCoordinatorModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CreateEventController implements Initializable {
    private EventCoordinatorModel eventCoordinatorModel;

    @FXML
    private CheckBox beerOption, womenOption, foodOption;

    @FXML
    private TextField cityField, endHourField, endMinField, eventNameField, postCodeField, startHourField, startMinField, streetField;

    @FXML
    private DatePicker endDateField, startDateField;


    @FXML
    private TextArea noteArea;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            eventCoordinatorModel = new EventCoordinatorModel();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void addEvent(ActionEvent event) {
        String eventName = eventNameField.getText().toUpperCase();
        String eventAddress = streetField.getText() + ", " + postCodeField.getText() + " " + cityField.getText();
        String notes = noteArea.getText();
        String startTime = startDateField.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyy")).toString() + " " + startHourField.getText() + ":" + startMinField.getText();
        String endTime = endDateField.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyy")).toString() + " " + endHourField.getText() + ":" + endMinField.getText();


        try {
            eventCoordinatorModel.createNewBarEvent(eventName, eventAddress, notes, startTime, endTime);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
