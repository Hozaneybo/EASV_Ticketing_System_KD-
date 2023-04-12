package gui.controller.coordinatorControllers;

import be.BarEvent;
import be.TicketType;
import gui.controller.LogInController;
import gui.model.FacadeModel;
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
    private TextField cityFieldE;

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
    private TextField postCodeFieldE;

    @FXML
    private DatePicker startDateFieldE;

    @FXML
    private TextField startHourFieldE;

    @FXML
    private TextField startMinFieldE;

    @FXML
    private TextField streetField;

    @FXML
    private CheckBox womenOption;

    @FXML
    private Label eventIdLabel;

    private FacadeModel facadeModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getFacadeModelInNewThread();

    }
    public CheckBox getBeerOption() {
        return beerOption;
    }

    public ComboBox<String> getCboxTicketTypeE() {
        return cboxTicketTypeE;
    }

    public TextField getCityFieldE() {
        return cityFieldE;
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

    public TextField getPostCodeFieldE() {
        return postCodeFieldE;
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

    public TextField getStreetField() {
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
        String eventAddress = streetField.getText() + ", " + postCodeFieldE.getText() + " " + cityFieldE.getText();
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


    private void getFacadeModelInNewThread()
    {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                facadeModel = new FacadeModel();
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

}
