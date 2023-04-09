package gui.controller.adminControllers;

import gui.controller.coordinatorControllers.EventViewController;
import gui.model.AdminModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

    @FXML
    private VBox eventBox;
    private AdminModel adminModel;






    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            adminModel = new AdminModel();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    void createCoordinator(ActionEvent event) {
        eventBox.getChildren().clear();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/adminGUI/NewEventCoordinator.fxml"));
        Node node = null;
        try {
            node = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        NewEventCoordinatorController controller = loader.getController();
        eventBox.getChildren().add(node);
    }

    @FXML
    void getAllCoordinators(ActionEvent event) {
        eventBox.getChildren().clear();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/adminGUI/CoordinatorsTableView.fxml"));
        Node node = null;
        try {
            node = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CoordinatorsTableView controller = loader.getController();
        eventBox.getChildren().add(node);

    }



    @FXML
    private  void showAllEvents() {
        eventBox.getChildren().clear();

        int eventsNumber = adminModel.getObservableEvents().size();

        if ( eventsNumber != 0) {
            for (int i = 0; i < eventsNumber; i++) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/coordinatorGUI/EventView.fxml"));
                    Node node = loader.load();
                    EventViewController controller = loader.getController();
                    controller.getEventNameLbl().setText(adminModel.getObservableEvents().get(i).getEventName());
                    controller.getEventAddressLbl().setText(adminModel.getObservableEvents().get(i).getEventAddress());
                    controller.getEventNotes().setText(adminModel.getObservableEvents().get(i).getNotes());
                    controller.getStartTimeLbl().setText(adminModel.getObservableEvents().get(i).getStartTime());
                    controller.getEndTimeLbl().setText(adminModel.getObservableEvents().get(i).getEndTime());
                    eventBox.getChildren().add(node);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @FXML
    private void logOut(ActionEvent event) {
        Node source = (Node) event.getSource();
        javafx.stage.Stage stage = (javafx.stage.Stage) source.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/customerGUI/CustomerView.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
