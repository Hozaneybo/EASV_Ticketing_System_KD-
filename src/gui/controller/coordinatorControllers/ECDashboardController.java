package gui.controller.coordinatorControllers;


import gui.model.EventCoordinatorModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/coordinatorGUI/EventView.fxml"));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/coordinatorGUI/CreateEventView.fxml"));
        Node node = null;
        try {
            node = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        eventBox.getChildren().add(node);
    }

    public void NewEventCoordinator(ActionEvent event) throws IOException {
        try {
            // Load the FXML file for the event coordinator form
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/adminGUI/NewEventCoordinator.fxml"));
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

    public void logOut(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
       Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/customerGUI/CustomerView.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void email(ActionEvent actionEvent) {

        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/coordinatorGUI/EmailWindow.fxml"));
        Node node =  loader.load();
        eventBox.getChildren().add(node);

    }catch (Exception e){
            e.printStackTrace();
        }
    }
}
