package gui.controller.coordinatorControllers;

import be.BarEvent;
import be.TicketType;
import gui.controller.LogInController;
import gui.model.FacadeModel;
import gui.model.FacadeModelLoader;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class EditEventController implements Initializable {

    @FXML
    private CheckBox beerOption;

    @FXML
    private ComboBox<String> cboxTicketTypeE;


    @FXML
    private DatePicker endDateFieldE;

    @FXML
    private TextField endHourFieldE;

    @FXML
    private TextField endMinFieldE;

    @FXML
    private TextField eventNameFieldE;

    @FXML
    private CheckBox foodOption;

    @FXML
    private TextArea noteAreaE;


    @FXML
    private DatePicker startDateFieldE;

    @FXML
    private TextField startHourFieldE;

    @FXML
    private TextField startMinFieldE;

    @FXML
    private TextArea streetField;

    @FXML
    private CheckBox womenOption;

    @FXML
    private Label eventIdLabel;

    private FacadeModelLoader facadeModelLoader;
    private FacadeModel facadeModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        facadeModelLoader = FacadeModelLoader.getInstance();
        facadeModel = facadeModelLoader.getFacadeModel();
        fieldsProperties();

    }
    public CheckBox getBeerOption() {
        return beerOption;
    }

    public ComboBox<String> getCboxTicketTypeE() {
        return cboxTicketTypeE;
    }


    public Label getEventIdLabel() {
        return eventIdLabel;
    }

    public DatePicker getEndDateFieldE() {
        return endDateFieldE;
    }

    public TextField getEndHourFieldE() {
        return endHourFieldE;
    }

    public TextField getEndMinFieldE() {
        return endMinFieldE;
    }

    public TextField getEventNameFieldE() {
        return eventNameFieldE;
    }

    public CheckBox getFoodOption() {
        return foodOption;
    }

    public TextArea getNoteAreaE() {
        return noteAreaE;
    }


    public DatePicker getStartDateFieldE() {
        return startDateFieldE;
    }

    public TextField getStartHourFieldE() {
        return startHourFieldE;
    }

    public TextField getStartMinFieldE() {
        return startMinFieldE;
    }

    public TextArea getStreetTextArea() {
        return streetField;
    }

    public CheckBox getWomenOption() {
        return womenOption;
    }

    @FXML
    void submitEditing(ActionEvent event) {
        //TODO submitEditing BarEvent
        int eventId = Integer.parseInt(eventIdLabel.getText());
        String eventName = eventNameFieldE.getText().toUpperCase();
        String eventAddress = streetField.getText();
        String notes = noteAreaE.getText();
        String startTime = null;
        if (startDateFieldE.getValue() != null) {
            startTime = startDateFieldE.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyy")).toString() + " " + startHourFieldE.getText() + ":" + startMinFieldE.getText();
        }
        String endTime = null;
        if (endDateFieldE.getValue() != null) {
            endTime = endDateFieldE.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyy")).toString() + " " + endHourFieldE.getText() + ":" + endMinFieldE.getText();
        }

        TicketType type = TicketType.valueOf(cboxTicketTypeE.getValue());

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/LogIn.fxml"));
            Parent root = loader.load();

            LogInController controller = loader.getController();
            int coordinator_id = controller.getCoordinatorId();
            BarEvent toBeUpdated = new BarEvent(eventId, eventName, eventAddress, notes, startTime, endTime, type, coordinator_id);

            facadeModel.getEventCoordinatorModel().updateBarEvent(toBeUpdated);


        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    private void fieldsProperties() {
        endDateFieldE.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (startDateFieldE.getValue() != null && newValue != null && newValue.isBefore(startDateFieldE.getValue())) {
                endDateFieldE.setValue(startDateFieldE.getValue());
            }
        });
        startHourFieldE.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // focus lost
                String hourText = startHourFieldE.getText();
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
                startHourFieldE.setText(hourText);
            }
        });
        startMinFieldE.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // focus lost
                String minText = startMinFieldE.getText();
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
                startMinFieldE.setText(minText);
            }
        });
        startHourFieldE.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                startHourFieldE.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        startMinFieldE.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                startMinFieldE.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        endHourFieldE.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // focus lost
                String hourText = endHourFieldE.getText();
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
                endHourFieldE.setText(hourText);
            }
        });
        endMinFieldE.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // focus lost
                String minText = endMinFieldE.getText();
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
                endMinFieldE.setText(minText);
            }
        });
        endHourFieldE.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                endHourFieldE.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        endMinFieldE.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                endMinFieldE.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

}
