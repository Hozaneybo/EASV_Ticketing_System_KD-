package gui.controller.adminControllers;

import gui.model.FacadeModel;
import gui.model.FacadeModelLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class NewEventCoordinatorController implements Initializable {

    @FXML
    private TextField FullNameTXT, UserNameTXT, PasswordTXT;
    private FacadeModelLoader facadeModelLoader;
    private FacadeModel facadeModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        facadeModelLoader = FacadeModelLoader.getInstance();
        facadeModel = facadeModelLoader.getFacadeModel();
    }

    public void CreateNewCoordinator(ActionEvent event) {
        String fullName = FullNameTXT.getText();
        String userName = UserNameTXT.getText();
        String password = PasswordTXT.getText();

        try {
            facadeModel.getAdminModel().createNewEventCoordinator(fullName, userName, password);

            FullNameTXT.clear();
            UserNameTXT.clear();
            PasswordTXT.clear();
            facadeModel.getAlert("", "Create a new coordinator", "You have successfully created an event coordinator ..!", Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            facadeModel.getAlert("Data connection error", "something went wrong", e.getMessage(), Alert.AlertType.ERROR);
        }

    }
}