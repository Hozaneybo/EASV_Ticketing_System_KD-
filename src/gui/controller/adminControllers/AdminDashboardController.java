package gui.controller.adminControllers;

import gui.controller.coordinatorControllers.EventViewController;
import gui.model.FacadeModel;
import gui.model.FacadeModelLoader;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

    @FXML
    private VBox eventBox;

    @FXML
    private Label userName;
    private FacadeModelLoader facadeModelLoader;
    private FacadeModel facadeModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        facadeModelLoader = FacadeModelLoader.getInstance();
        facadeModel = facadeModelLoader.getFacadeModel();

    }

    @FXML
    void createCoordinator(ActionEvent event) {
        eventBox.getChildren().clear();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/adminGUI/NewEventCoordinator.fxml"));
        Node node = null;
        try {
            node = loader.load();
        } catch (IOException e) {
            facadeModel.getAlert("Database Error", "Something went wrong!", e.getMessage(), Alert.AlertType.ERROR);
        }
        eventBox.getChildren().add(node);
    }

    @FXML
    void getAllCoordinators(ActionEvent event) {
        eventBox.getChildren().clear();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/adminGUI/CoordinatorsTableView.fxml"));
        Node node = null;
        try {
            node = loader.load();
        } catch (IOException e) {
            facadeModel.getAlert("Database Error", "Something went wrong!", e.getMessage(), Alert.AlertType.ERROR);
        }
        eventBox.getChildren().add(node);

    }

    @FXML
    private void showAllEvents() {
        eventBox.getChildren().clear();

        int eventsNumber = facadeModel.getAdminModel().getObservableEvents().size();

        if (eventsNumber != 0) {
            for (int i = 0; i < eventsNumber; i++) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/coordinatorGUI/EventView.fxml"));
                    Node node = loader.load();
                    EventViewController controller = loader.getController();
                    controller.getEventNameLbl().setText(facadeModel.getAdminModel().getObservableEvents().get(i).getEventName());
                    controller.getEventAddressLbl().setText(facadeModel.getAdminModel().getObservableEvents().get(i).getEventAddress());
                    controller.getEventNotes().setText(facadeModel.getAdminModel().getObservableEvents().get(i).getNotes());
                    controller.getStartTimeLbl().setText(facadeModel.getAdminModel().getObservableEvents().get(i).getStartTime());
                    controller.getEndTimeLbl().setText(facadeModel.getAdminModel().getObservableEvents().get(i).getEndTime());
                    controller.getEventIdLabel().setText(String.valueOf(facadeModel.getAdminModel().getObservableEvents().get(i).getId()));
                    controller.getUpdateBtn().setVisible(false);
                    eventBox.getChildren().add(node);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    facadeModel.getAlert("Database Error", "Something went wrong!", ex.getMessage(), Alert.AlertType.ERROR);
                }
            }
        }
    }

    @FXML
    private void logOut(ActionEvent event) {
        Node source = (Node) event.getSource();
        javafx.stage.Stage stage = (javafx.stage.Stage) source.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/customerGUI/CustomerView.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            facadeModel.getAlert("Database Error", "Something went wrong!", e.getMessage(), Alert.AlertType.ERROR);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public Label getUserName() {
        return userName;
    }
}