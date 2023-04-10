package gui.controller.adminControllers;


import be.EventCoordinator;
import gui.model.FacadeModel;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    private FacadeModel facadeModel;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getFacadeModelInNewThread();
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

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    void submitEditing(ActionEvent event) {
        EventCoordinator toBeUpdated = new EventCoordinator(parseInt(coordinatorIdLabel.getText()), coordinatorNameE.getText(), coordinatorUsernameE.getText(), coordinatorPasswordE.getText());
        facadeModel.getAdminModel().updateEventCoordinator(toBeUpdated);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    private void getFacadeModelInNewThread() {
        Task < Void > task = new Task < Void > () {
            @Override
            protected Void call() throws Exception {
                facadeModel = new FacadeModel();
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }
}