package gui.controller.coordinatorControllers;
import be.TicketType;
import gui.controller.LogInController;
import gui.model.EventCoordinatorModel;
import gui.model.FacadeModel;
import gui.model.FacadeModelLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
public class CreateEventController implements Initializable {
    @FXML
    private ComboBox < String > cboxTicketType;
    private EventCoordinatorModel eventCoordinatorModel;
    @FXML
    private CheckBox beerOption, womenOption, foodOption;
    @FXML
    private TextField cityField, endHourField, endMinField, eventNameField, postCodeField, startHourField, startMinField, streetField;
    @FXML
    private DatePicker endDateField, startDateField;
    @FXML
    private TextArea noteArea;
    private FacadeModelLoader facadeModelLoader;
    private FacadeModel facadeModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        facadeModelLoader = FacadeModelLoader.getInstance();
        facadeModel = facadeModelLoader.getFacadeModel();
        fieldsProperties();
        cboxTicketType.setItems(facadeModel.getEventCoordinatorModel().getTypes());
    }
    @FXML
    void addEvent(ActionEvent event) {
        String eventName = eventNameField.getText().toUpperCase();
        String eventAddress = streetField.getText() + ", " + postCodeField.getText() + " " + cityField.getText();
        String notes = noteArea.getText();
        String startTime = null;
        if (startDateField.getValue() != null) {
            startTime = startDateField.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyy")).toString() + " " + startHourField.getText() + ":" + startMinField.getText();
        }
        String endTime = null;
        if (endDateField.getValue() != null) {
            endTime = endDateField.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyy")).toString() + " " + endHourField.getText() + ":" + endMinField.getText();
        }
        TicketType type = TicketType.valueOf(cboxTicketType.getValue());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/LogIn.fxml"));
            Parent root = loader.load();
            LogInController controller = loader.getController();
            int coordinator_id = controller.getCoordinatorId();
            eventCoordinatorModel.createNewBarEvent(eventName, eventAddress, notes, startTime, endTime, type, coordinator_id);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void fieldsProperties() {
        endDateField.valueProperty().addListener((observable, oldValue, newValue) -> {
        if (startDateField.getValue() != null && newValue != null && newValue.isBefore(startDateField.getValue())) {
            endDateField.setValue(startDateField.getValue());
        }
        });
        startHourField.focusedProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue) { // focus lost
            String hourText = startHourField.getText();
            if (hourText.length() == 1) {
                // if input consists of one digit, add a leading 0
                hourText = "0" + hourText;
            }
            int hour = Integer.parseInt(hourText);
            if (hour < 0 || hour > 23) {
                // if input is outside the range [0, 23], set to the closest valid value
                hour = Math.min(Math.max(0, hour), 23);
                hourText = String.format("%02d", hour);
            }
            startHourField.setText(hourText);
        }
        });
        startMinField.focusedProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue) { // focus lost
            String minText = startMinField.getText();
            if (minText.length() == 1) {
                // if input consists of one digit, add a leading 0
                minText = "0" + minText;
            }
            int minute = Integer.parseInt(minText);
            if (minute < 0 || minute > 59) {
                // if input is outside the range [0, 23], set to the closest valid value
                minute = Math.min(Math.max(0, minute), 59);
                minText = String.format("%02d", minute);
            }
            startMinField.setText(minText);
        }
        });
        startHourField.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) {
            startHourField.setText(newValue.replaceAll("[^\\d]", ""));
        }
        });
        startMinField.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) {
            startMinField.setText(newValue.replaceAll("[^\\d]", ""));
        }
        });
        endHourField.focusedProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue) { // focus lost
            String hourText = endHourField.getText();
            if (hourText.length() == 1) {
                // if input consists of one digit, add a leading 0
                hourText = "0" + hourText;
            }
            int hour = Integer.parseInt(hourText);
            if (hour < 0 || hour > 23) {
                // if input is outside the range [0, 23], set to the closest valid value
                hour = Math.min(Math.max(0, hour), 23);
                hourText = String.format("%02d", hour);
            }
            endHourField.setText(hourText);
        }
        });
        endMinField.focusedProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue) { // focus lost
            String minText = endMinField.getText();
            if (minText.length() == 1) {
                // if input consists of one digit, add a leading 0
                minText = "0" + minText;
            }
            int minute = Integer.parseInt(minText);
            if (minute < 0 || minute > 59) {
                // if input is outside the range [0, 23], set to the closest valid value
                minute = Math.min(Math.max(0, minute), 59);
                minText = String.format("%02d", minute);
            }
            endMinField.setText(minText);
        }
        });
        endHourField.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) {
            endHourField.setText(newValue.replaceAll("[^\\d]", ""));
        }
        });
        endMinField.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) {
            endMinField.setText(newValue.replaceAll("[^\\d]", ""));
        }
        });
    }
}