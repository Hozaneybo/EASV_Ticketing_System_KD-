package gui.controller;

import be.Admin;
import be.EventCoordinator;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import gui.controller.adminControllers.AdminDashboardController;
import gui.controller.coordinatorControllers.ECDashboardController;
import gui.model.FacadeModel;
import gui.model.FacadeModelLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
    public Button logInButton;
    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;


    private static int coordinatorId;

    private FacadeModelLoader facadeModelLoader;
    private FacadeModel facadeModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        facadeModelLoader = FacadeModelLoader.getInstance();
        facadeModel = facadeModelLoader.getFacadeModel();

    }
    public int getCoordinatorId() {
        return LogInController.coordinatorId;
    }



       @FXML
       private void handleLogIn(ActionEvent actionEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();


           try {
            Admin admin = facadeModel.getLogInModel().adminLogIn(username, password);
            EventCoordinator coordinator = null;
            try {
                coordinator = facadeModel.getLogInModel().coordinatorLogIn(username, password);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if (admin != null) {
                // Login successful, navigate to the next screen
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/adminGUI/AdminDashboard.fxml"));
                Parent root = loader.load();
                AdminDashboardController controller = loader.getController();
                controller.getUserName().setText(admin.getName());
                Scene scene = new Scene(root);
                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();

            } else if (coordinator != null) {

                // Login successful, navigate to the next screen
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/coordinatorGUI/CoordinatorDashboard.fxml"));
                Parent root = loader.load();
                ECDashboardController controller = loader.getController();
                controller.getUserName().setText(coordinator.getFullName());
                Scene scene = new Scene(root);
                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(scene);
                coordinatorId = coordinator.getId();
                stage.setResizable(false);
                stage.show();


            }
            else {
                // Login failed, show an error message
                facadeModel.getAlert("Login error", "Invalid Data",
                        "Please enter the correct information", Alert.AlertType.ERROR);
            }
        } catch (IOException e) {
            // Handle FXML loading errors
            facadeModel.getAlert("FXML loding error", "Something went wrong!", e.getMessage(), Alert.AlertType.ERROR);
        } catch (SQLServerException e) {
            // Handle database connection errors
            facadeModel.getAlert("Database connection error", "Something went wrong!", e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
