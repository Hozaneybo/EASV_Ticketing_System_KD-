package gui.controller.adminControllers;

import be.EventCoordinator;
import gui.model.FacadeModel;

import gui.model.FacadeModelLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class EditCoordinatorViewController implements Initializable {
    @FXML
    private Label coordinatorIdLabel;
    @FXML
    private TextField coordinatorNameE, coordinatorPasswordE, coordinatorUsernameE;
    private FacadeModelLoader facadeModelLoader;
    private FacadeModel facadeModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        facadeModelLoader = FacadeModelLoader.getInstance();
        facadeModel = facadeModelLoader.getFacadeModel();
    }
    public Label getCoordinatorIdLabel() {
        return coordinatorIdLabel;
    }
    public TextField getCoordinatorNameE() {
        return coordinatorNameE;
    }
    public TextField getCoordinatorPasswordE() {
        return coordinatorPasswordE;
    }
    public TextField getCoordinatorUsernameE() {
        return coordinatorUsernameE;
    }
    @FXML
    void cancelEditing(ActionEvent event) {

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    void submitEditing(ActionEvent event) {
        EventCoordinator toBeUpdated = new EventCoordinator(parseInt(coordinatorIdLabel.getText()), coordinatorNameE.getText(), coordinatorUsernameE.getText(), coordinatorPasswordE.getText());
        try {
            facadeModel.getAdminModel().updateEventCoordinator(toBeUpdated);
        } catch (Exception e) {
            facadeModel.getAlert("Data connection error", "something went wrong", e.getMessage(), Alert.AlertType.ERROR);
        }

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}