package gui.controller.adminControllers;

import be.Admin;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import gui.model.LogInModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminViewController implements Initializable {

    @FXML
    private TextField Password;

    @FXML
    private TextField Username;

    private LogInModel logInModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        logInModel = new LogInModel();

    }

    @FXML
    void handleLogIn(ActionEvent event) throws IOException, SQLServerException {
        String username = Username.getText();
        String password = Password.getText();

        try {
            Admin admin = logInModel.adminLogIn(username, password);

        if(admin != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/MainView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("tis is new Window");
            stage.show();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
        }
        catch (Exception e) {
                e.printStackTrace();
        }
    }
}
