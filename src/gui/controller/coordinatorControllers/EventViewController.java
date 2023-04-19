package gui.controller.coordinatorControllers;

import be.BarEvent;
import be.TicketType;
import gui.model.EventCoordinatorModel;
import gui.model.FacadeModel;
import gui.model.FacadeModelLoader;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class EventViewController implements Initializable {

    @FXML
    private Button updateBtn;

    @FXML
    private Label endTimeLbl, eventAddressLbl, eventNameLbl, eventNotes, startTimeLbl, eventIdLabel;

    private FacadeModelLoader facadeModelLoader;
    private FacadeModel facadeModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        facadeModelLoader = FacadeModelLoader.getInstance();
        facadeModel = facadeModelLoader.getFacadeModel();

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

    public Button getUpdateBtn() {
        return updateBtn;
    }

    public Label getEventIdLabel() {
        return eventIdLabel;
    }

    public void deleteEvent(ActionEvent actionEvent) {

        int eventId = Integer.parseInt(eventIdLabel.getText());
        BarEvent toBeDeleted = new BarEvent(eventId);
        try {
            facadeModel.getEventCoordinatorModel().deleteBarEvent(toBeDeleted);
        } catch (Exception e) {
            facadeModel.getAlert("Database Error", "Something went wrong!", e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    public void updateEvent(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/coordinatorGUI/EditEventView.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        EditEventController controller = loader.getController();

        // Pass the data to the UpdateEventViewController
        controller.getEventNameFieldE().setText(eventNameLbl.getText());
        controller.getStreetTextArea().setText(eventAddressLbl.getText());
        String startDateString = startTimeLbl.getText().substring(0, 10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate startDate = LocalDate.parse(startDateString, formatter);
        controller.getStartDateFieldE().setValue(startDate);
        controller.getStartHourFieldE().setText(startTimeLbl.getText().substring(11, 13));
        controller.getStartMinFieldE().setText(startTimeLbl.getText().substring(14));
        String endDateString = endTimeLbl.getText().substring(0, 10);
        LocalDate endDate = LocalDate.parse(endDateString, formatter);
        controller.getEndDateFieldE().setValue(endDate);
        controller.getEndHourFieldE().setText(endTimeLbl.getText().substring(11, 13));
        controller.getEndMinFieldE().setText(endTimeLbl.getText().substring(14));
        String[] options = {"STANDARD", "CUSTOMIZED", "SPECIAL"};
        controller.getCboxTicketTypeE().getItems().addAll(options);
        controller.getEventIdLabel().setText(eventIdLabel.getText());

        Stage eventCoordinatorStage = new Stage();
        eventCoordinatorStage.setTitle("Update This Event");
        Scene scene = new Scene(root);
        eventCoordinatorStage.setScene(scene);

        // Show the new stage
        eventCoordinatorStage.setResizable(false);
        eventCoordinatorStage.show();

    }
}
