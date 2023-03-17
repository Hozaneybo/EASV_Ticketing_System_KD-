package gui.controller;

import be.BarEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.EventCoordinatorModel;

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
}
