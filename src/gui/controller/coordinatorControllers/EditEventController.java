package gui.controller.coordinatorControllers;

import be.BarEvent;
import gui.model.FacadeModel;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
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
