package gui.controller;

import be.BarEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import gui.model.EventCoordinatorModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ECDashboardController implements Initializable {

    private EventCoordinatorModel eventCoordinatorModel;

    @FXML
    private TableView<BarEvent> eventsTableView;

    
    @FXML
    private TableColumn<BarEvent, String> eventNameColumn, eventAddressColumn, eventStartTimeColumn, eventNotesColumn, eventEndTimeColumn;
    @FXML
    private TableColumn<BarEvent, Integer> eventIdColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            eventCoordinatorModel = new EventCoordinatorModel();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(eventCoordinatorModel.getObservableEvents().size() != 0)
        {
            showAllEvents();
        }


    }

    private void showAllEvents() {
        eventsTableView.setItems(eventCoordinatorModel.getObservableEvents());
        eventIdColumn.setCellValueFactory(new PropertyValueFactory<BarEvent, Integer>("id"));
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<BarEvent, String>("eventName"));
        eventAddressColumn.setCellValueFactory(new PropertyValueFactory<BarEvent, String>("eventAddress"));
        eventStartTimeColumn.setCellValueFactory(new PropertyValueFactory<BarEvent, String>("startTime"));
        eventNotesColumn.setCellValueFactory(new PropertyValueFactory<BarEvent, String>("notes"));
        eventEndTimeColumn.setCellValueFactory(new PropertyValueFactory<BarEvent, String>("endTime"));

    }

    public void NewEventCoordinator(ActionEvent event) throws IOException {
        try {
            // Load the FXML file for the event coordinator form
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/NewEventCoordinator.fxml"));
            Parent root = loader.load();

            // Create a new stage (window) for the event coordinator form
            Stage eventCoordinatorStage = new Stage();
            eventCoordinatorStage.setTitle("New Event Coordinator");

            // Set the scene for the stage with the FXML contents
            Scene scene = new Scene(root);
            eventCoordinatorStage.setScene(scene);

            // Show the event coordinator form
            eventCoordinatorStage.show();
        } catch (IOException e) {
            // Handle any errors that occur while loading the FXML file
            e.printStackTrace();
        }
    }
}
