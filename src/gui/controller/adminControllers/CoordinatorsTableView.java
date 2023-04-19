package gui.controller.adminControllers;

import be.EventCoordinator;
import gui.model.FacadeModel;
import gui.model.FacadeModelLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CoordinatorsTableView implements Initializable {

    @FXML
    private TableView < EventCoordinator > coordinatorTable;

    @FXML
    private TableColumn < EventCoordinator, Integer > idColumn;

    @FXML
    private TableColumn < EventCoordinator, String > nameColumn;

    @FXML
    private TableColumn < EventCoordinator, String > usernameColumn;

    private EventCoordinator selectedEventCoordinator;

    private FacadeModelLoader facadeModelLoader;
    private FacadeModel facadeModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        facadeModelLoader = FacadeModelLoader.getInstance();
        facadeModel = facadeModelLoader.getFacadeModel();

        if (facadeModel.getAdminModel().getObservableEventCoordinator().size() != 0) {
            coordinatorTable.setItems(facadeModel.getAdminModel().getObservableEventCoordinator());
            idColumn.setCellValueFactory(new PropertyValueFactory < EventCoordinator, Integer > ("id"));
            nameColumn.setCellValueFactory(new PropertyValueFactory < EventCoordinator, String > ("fullName"));
            usernameColumn.setCellValueFactory(new PropertyValueFactory < EventCoordinator, String > ("username"));

        }

    }

    public void updateCoordinator(ActionEvent actionEvent) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/adminGUI/EditCoordinatorView.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            facadeModel.getAlert("Database Error", "Something went wrong!", e.getMessage(), Alert.AlertType.ERROR);
        }
        if (facadeModel.getAdminModel().getObservableEventCoordinator().size() != 0)
            selectedEventCoordinator = coordinatorTable.getSelectionModel().getSelectedItem();
        try {
            EditCoordinatorViewController controller = loader.getController();
            controller.getCoordinatorIdLabel().setText(String.valueOf(selectedEventCoordinator.getId()));
            controller.getCoordinatorNameE().setText(selectedEventCoordinator.getFullName());
            controller.getCoordinatorUsernameE().setText(selectedEventCoordinator.getUsername());
            Stage stage = new Stage();
            stage.setTitle("Update EventCoordinator");
            Scene scene = new Scene(root);
            stage.setScene(scene);

            // Show the new stage
            stage.setResizable(false);
            stage.show();
            controller.getCoordinatorPasswordE().setPromptText("New Password");
        } catch (Exception e) {
            facadeModel.getAlert("Error", "Something went wrong!", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void deleteCoordinator(ActionEvent actionEvent) {
        if (facadeModel.getAdminModel().getObservableEventCoordinator().size() != 0) {
            selectedEventCoordinator = coordinatorTable.getSelectionModel().getSelectedItem();
            try {
            Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmDialog.setTitle("Confirm Deletion");
            confirmDialog.setHeaderText("Are you sure you want to delete coordinator " + selectedEventCoordinator.getFullName() + "?");
            confirmDialog.setContentText("Click OK to delete or Cancel to exit without deleting.");
            Optional<ButtonType> result = confirmDialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                facadeModel.getAdminModel().deleteEventCoordinator(selectedEventCoordinator);
            }
            } catch (Exception e) {
                facadeModel.getAlert("Error", "Something went wrong!", e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }
}