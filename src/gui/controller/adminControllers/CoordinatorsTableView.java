package gui.controller.adminControllers;

import be.EventCoordinator;
import gui.controller.coordinatorControllers.EditEventController;
import gui.model.AdminModel;
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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CoordinatorsTableView implements Initializable {

    @FXML
    private TableView<EventCoordinator> coordinatorTable;

    @FXML
    private TableColumn<EventCoordinator, Integer> idColumn;

    @FXML
    private TableColumn<EventCoordinator, String> nameColumn;


    @FXML
    private TableColumn<EventCoordinator, String> usernameColumn;

    private EventCoordinator selectedEventCoordinator;

    AdminModel adminModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            adminModel = new AdminModel();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(adminModel.getObservableEventCoordinator().size() != 0)
        {
            coordinatorTable.setItems(adminModel.getObservableEventCoordinator());
            idColumn.setCellValueFactory(new PropertyValueFactory<EventCoordinator, Integer>("id"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<EventCoordinator, String>("fullName"));
            usernameColumn.setCellValueFactory(new PropertyValueFactory<EventCoordinator, String>("username"));

        }

    }



    public void updateCoordinator(ActionEvent actionEvent) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/adminGUI/EditCoordinatorView.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(adminModel.getObservableEventCoordinator().size() != 0)
            selectedEventCoordinator = coordinatorTable.getSelectionModel().getSelectedItem();
        EditCoordinatorViewController controller = loader.getController();
        controller.getCoordinatorIdLabel().setText(String.valueOf(selectedEventCoordinator.getId()));
        controller.getCoordinatorNameE().setText(selectedEventCoordinator.getFullName());
        controller.getCoordinatorUsernameE().setText(selectedEventCoordinator.getUsername());
        controller.getCoordinatorPasswordE().setPromptText("New Password");

        Stage stage = new Stage();
        stage.setTitle("Update EventCoordinator");
        Scene scene = new Scene(root);
        stage.setScene(scene);

        // Show the new stage
        stage.setResizable(false);
        stage.show();
    }

    public void deleteCoordinator(ActionEvent actionEvent) {
        if(adminModel.getObservableEventCoordinator().size() != 0)
            selectedEventCoordinator = coordinatorTable.getSelectionModel().getSelectedItem();

        adminModel.deleteEventCoordinator(selectedEventCoordinator);
    }
}
