package gui.controller.adminControllers;

import gui.controller.coordinatorControllers.EventViewController;
import gui.controller.costumerControllers.CustomerViewController;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
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
        try {
            facadeModel.getAdminModel().refreshEventListView();
        } catch (SQLException e) {
            facadeModel.getAlert("Error", "Something went wrong!", e.getMessage(), Alert.AlertType.ERROR);        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/adminGUI/NewEventCoordinator.fxml"));
        Node node = null;
        try {
            node = loader.load();
        } catch (IOException e) {
            facadeModel.getAlert("Error", "Something went wrong!", e.getMessage(), Alert.AlertType.ERROR);
        }
        eventBox.getChildren().add(node);
    }

    @FXML
    void getAllCoordinators(ActionEvent event) {
        eventBox.getChildren().clear();
        try {
            facadeModel.getAdminModel().refreshEventListView();
        } catch (SQLException e) {
            facadeModel.getAlert("Error", "Something went wrong!", e.getMessage(), Alert.AlertType.ERROR);        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/adminGUI/CoordinatorsTableView.fxml"));
        Node node = null;
        try {
            node = loader.load();
        } catch (IOException e) {
            facadeModel.getAlert("Error", "Something went wrong!", e.getMessage(), Alert.AlertType.ERROR);
        }
        eventBox.getChildren().add(node);

    }

    public void showAllEvents() {
        eventBox.getChildren().clear();
        try {
            facadeModel.getAdminModel().refreshEventListView();
        } catch (SQLException e) {
            facadeModel.getAlert("Error", "Something went wrong!", e.getMessage(), Alert.AlertType.ERROR);
        }

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
                    facadeModel.getAlert("Error", "Something went wrong!", ex.getMessage(), Alert.AlertType.ERROR);
                }
            }
        }
    }

    @FXML
    private void logOut(ActionEvent event) {
        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Confirm Logout");
        confirmDialog.setHeaderText("Are you sure you want to log out?");
        confirmDialog.setContentText("Click OK to confirm or Cancel to stay logged in.");

        Optional<ButtonType> result = confirmDialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/customerGUI/CustomerView.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                facadeModel.getAlert("Error", "Something went wrong!", e.getMessage(), Alert.AlertType.ERROR);
            }
            CustomerViewController controller = loader.getController();
            controller.showAllEvents();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }


    public Label getUserName() {
        return userName;
    }
}