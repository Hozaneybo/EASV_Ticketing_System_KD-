package gui.controller.coordinatorControllers;

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
import java.util.Optional;
import java.util.ResourceBundle;

public class ECDashboardController implements Initializable {
    @FXML
    private VBox eventBox;
    @FXML
    private Label userName;

    private FacadeModelLoader facadeModelLoader;
    private FacadeModel facadeModel;
    private int eventsNumber;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        facadeModelLoader = FacadeModelLoader.getInstance();
        facadeModel = facadeModelLoader.getFacadeModel();
    }
    public void showAllEvents() {
        eventBox.getChildren().clear();
        try {
            facadeModel.getEventCoordinatorModel().refreshEventListView();
        } catch (Exception e) {
            facadeModel.getAlert("Error", "Something went wrong ... !", e.getMessage(), Alert.AlertType.ERROR);
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
                    facadeModel.getAlert("Error", "Something went wrong ... !", ex.getMessage(), Alert.AlertType.ERROR);
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
            facadeModel.getAlert("Error", "Something went wrong ... !", e.getMessage(), Alert.AlertType.ERROR);
        }
        eventBox.getChildren().add(node);
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



    public void email(ActionEvent actionEvent) {
        eventBox.getChildren().clear();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/coordinatorGUI/EmailWindow.fxml"));
            Node node = loader.load();
            eventBox.getChildren().add(node);

        } catch (Exception e) {
            facadeModel.getAlert("Error", "Something went wrong ... !", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public Label getUserName() {
        return userName;
    }
}