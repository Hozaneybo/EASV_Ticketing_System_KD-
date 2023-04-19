package gui.controller.adminControllers;


import gui.model.AdminModel;
import gui.model.FacadeModel;
import gui.model.FacadeModelLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
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


    public void CreateNewCoordinator(ActionEvent event)  {
        String fullName = FullNameTXT.getText();
        String userName = UserNameTXT.getText();
        String password = PasswordTXT.getText();

        try {
            facadeModel.getAdminModel().createNewEventCoordinator(fullName, userName, password);

            FullNameTXT.clear();
            UserNameTXT.clear();
            PasswordTXT.clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "You have successfully created an event coordinator ..!");
            alert.show();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
