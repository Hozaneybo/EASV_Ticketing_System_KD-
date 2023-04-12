package gui.controller.coordinatorControllers;

import be.BarEvent;
import be.TicketType;
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
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class EditEventController implements Initializable {

    @FXML
    private CheckBox beerOption;

    @FXML
    private ComboBox<String> cboxTicketTypeE;

    @FXML
    private TextField cityFieldE, endHourFieldE, endMinFieldE, eventNameFieldE, postCodeFieldE, startMinFieldE, startHourFieldE, streetField;

    @FXML
    private DatePicker endDateFieldE, startDateFieldE;

    @FXML
    private CheckBox foodOption;

    @FXML
    private TextArea noteAreaE;



    @FXML
    private CheckBox womenOption;

    @FXML
    private Label eventIdLabel;

    private FacadeModel facadeModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getFacadeModelInNewThread();
        try {
            facadeModel = new FacadeModel();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        cboxTicketTypeE.setItems(facadeModel.getEventCoordinatorModel().getTypes());

    }

    @FXML
    void submitEditing(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/coordinatorGUI/EventView.fxml"));
        Parent root = null;
       try {
            root = loader.load();
       } catch (IOException e) {
           throw new RuntimeException(e);
       }

        EventViewController controller = loader.getController();

        int event_id = controller.getEventId();

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

        BarEvent barEvent = new BarEvent(event_id, eventName, eventAddress, notes, startTime, endTime, type);


        try {
            facadeModel.getEventCoordinatorModel().updateBarEvent(barEvent);
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
