package gui.controller.adminControllers;


import gui.model.AdminModel;
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
    private AdminModel model;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            model = new AdminModel();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void CreateNewCoordinator(ActionEvent event)  {
        String fullName = FullNameTXT.getText();
        String userName = UserNameTXT.getText();
        String password = PasswordTXT.getText();

        try {
            model.createNewEventCoordinator(fullName, userName, password);

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
