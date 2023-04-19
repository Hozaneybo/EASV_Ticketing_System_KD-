package gui.controller.coordinatorControllers;

import gui.model.FacadeModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ECDashboardController implements Initializable {
    @FXML
    private VBox eventBox;
    @FXML
    private Label userName;

    private FacadeModel facadeModel;
    private int eventsNumber;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            facadeModel = new FacadeModel();
        } catch (SQLException e) {
            facadeModel.getAlert("Data connection error", "something went wrong", e.getMessage(), Alert.AlertType.ERROR);
        }

    }
    @FXML
    private void showAllEvents() {
        eventBox.getChildren().clear();
        try {
            facadeModel.getEventCoordinatorModel().refreshEventListView();
        } catch (Exception e) {
            facadeModel.getAlert("Data connection error", "something went wrong", e.getMessage(), Alert.AlertType.ERROR);
        }
        eventsNumber = facadeModel.getEventCoordinatorModel().getObservableEvents().size();

        if (eventsNumber != 0) {
            for (int i = 0; i < eventsNumber; i++) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/coordinatorGUI/EventView.fxml"));
                    Node node = loader.load();
                    EventViewController controller = loader.getController();
                    controller.getEventNameLbl().setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(i).getEventName());
                    controller.getEventAddressLbl().setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(i).getEventAddress());
                    controller.getEventNotes().setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(i).getNotes());
                    controller.getStartTimeLbl().setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(i).getStartTime());
                    controller.getEndTimeLbl().setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(i).getEndTime());
                    controller.getEventIdLabel().setText(String.valueOf(facadeModel.getEventCoordinatorModel().getObservableEvents().get(i).getId()));
                    eventBox.getChildren().add(node);
                } catch (IOException ex) {
                    facadeModel.getAlert("Data connection error", "something went wrong", ex.getMessage(), Alert.AlertType.ERROR);
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
        } catch (Exception e) {
            e.printStackTrace();
            facadeModel.getAlert("Data connection error", "something went wrong", e.getMessage(), Alert.AlertType.ERROR);
        }
        eventBox.getChildren().add(node);
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
            facadeModel.getAlert("Data connection error", "something went wrong", e.getMessage(), Alert.AlertType.ERROR);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void email(ActionEvent actionEvent) {
        eventBox.getChildren().clear();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/coordinatorGUI/EmailWindow.fxml"));
            Node node = loader.load();
            eventBox.getChildren().add(node);

        } catch (Exception e) {
            e.printStackTrace();
            facadeModel.getAlert("Data connection error", "something went wrong", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public Label getUserName() {
        return userName;
    }
}