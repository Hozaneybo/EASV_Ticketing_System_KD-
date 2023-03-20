package gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import model.EventCoordinatorModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ECDashboardController implements Initializable {
    @FXML
    private VBox eventBox;

    private EventCoordinatorModel eventCoordinatorModel;
    private int eventsNumber;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            eventCoordinatorModel = new EventCoordinatorModel();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    private void showAllEvents() {
        eventBox.getChildren().clear();
        try {
            eventCoordinatorModel.refreshEventListView();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        eventsNumber = eventCoordinatorModel.getObservableEvents().size();

        if ( eventsNumber != 0) {
            for (int i = 0; i < eventsNumber; i++) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/EventView.fxml"));
                    Node node = loader.load();
                    EventViewController controller = loader.getController();
                    controller.getEventNameLbl().setText(eventCoordinatorModel.getObservableEvents().get(i).getEventName());
                    controller.getEventAddressLbl().setText(eventCoordinatorModel.getObservableEvents().get(i).getEventAddress());
                    controller.getEventNotes().setText(eventCoordinatorModel.getObservableEvents().get(i).getNotes());
                    controller.getStartTimeLbl().setText(eventCoordinatorModel.getObservableEvents().get(i).getStartTime());
                    controller.getEndTimeLbl().setText(eventCoordinatorModel.getObservableEvents().get(i).getEndTime());
                    eventBox.getChildren().add(node);
                } catch (IOException ex) {

                }
            }
        }
    }
    @FXML
    private void createNewEvent(ActionEvent actionEvent) {

        eventBox.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/CreateEventView.fxml"));
        Node node = null;
        try {
            node = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        eventBox.getChildren().add(node);


    }
}
