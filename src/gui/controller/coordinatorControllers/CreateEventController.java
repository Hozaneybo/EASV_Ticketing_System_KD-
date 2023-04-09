package gui.controller.coordinatorControllers;


import be.TicketType;
import gui.model.EventCoordinatorModel;
import gui.model.FacadeModel;
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
    public ComboBox<String> cboxTicketType;

    private EventCoordinatorModel eventCoordinatorModel;

    @FXML
    private CheckBox beerOption, womenOption, foodOption;

    @FXML
    private TextField cityField, endHourField, endMinField, eventNameField, postCodeField, startHourField, startMinField, streetField;

    @FXML
    private DatePicker endDateField, startDateField;


    @FXML
    private TextArea noteArea;
    private FacadeModel facadeModel;

    private int getCoordinator_id;

    private int coordinator_id;


    public CreateEventController(int coordinator_id){
        this.coordinator_id = coordinator_id;

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            eventCoordinatorModel = new EventCoordinatorModel();
            facadeModel = new FacadeModel();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        cboxTicketType.setItems(facadeModel.getEventCoordinatorModel().getTypes());
    }

    public int getGetCoordinator_id() {
        return getCoordinator_id;
    }

    public void setGetCoordinator_id(int getCoordinator_id) {
        this.getCoordinator_id = getCoordinator_id;
    }

    @FXML
    void addEvent(ActionEvent event) {
        String eventName = eventNameField.getText().toUpperCase();
        String eventAddress = streetField.getText() + ", " + postCodeField.getText() + " " + cityField.getText();
        String notes = noteArea.getText();
        String startTime = null;
        if (startDateField.getValue() != null) {
            startTime = startDateField.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyy")).toString() + " " + startHourField.getText() + ":" + startMinField.getText();
        }
        String endTime = null;
        if (endDateField.getValue() != null) {
            endTime = endDateField.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyy")).toString() + " " + endHourField.getText() + ":" + endMinField.getText();
        }

        TicketType type = TicketType.valueOf(cboxTicketType.getValue());

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/LogIn.fxml"));
            Parent root = loader.load();

           // LogInController controller = loader.getController();
            //int coordinator_id = controller.getId();

            eventCoordinatorModel.createNewBarEvent(eventName, eventAddress, notes, startTime, endTime, type, coordinator_id);


        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
