package gui.controller.costumerControllers;

import gui.controller.LogIn;
import gui.model.CustomerModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerViewController implements Initializable {

    @FXML
    private VBox eventBox;

    private CustomerModel customerModel;

    int eventsNumber;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            customerModel = new CustomerModel();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    @FXML
    private void showAllEvents() {
        eventBox.getChildren().clear();
        try {
            customerModel.refreshEventListView();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        eventsNumber = customerModel.getObservableEvents().size();

        if ( eventsNumber != 0) {
            for (int i = 0; i < eventsNumber; i++) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/customerGUI/CustomerEventView.fxml"));
                    Node node = loader.load();
                    CustomerEventViewController controller = loader.getController();
                    controller.getEventNameLbl().setText(customerModel.getObservableEvents().get(i).getEventName());
                    controller.getEventAddressLbl().setText(customerModel.getObservableEvents().get(i).getEventAddress());
                    controller.getEventNotes().setText(customerModel.getObservableEvents().get(i).getNotes());
                    controller.getStartTimeLbl().setText(customerModel.getObservableEvents().get(i).getStartTime());
                    controller.getEndTimeLbl().setText(customerModel.getObservableEvents().get(i).getEndTime());
                    eventBox.getChildren().add(node);
                } catch (IOException ex) {

                }
            }
        }
    }

    public void logIn(ActionEvent actionEvent) {

        eventBox.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/LogIn.fxml"));
        Node node = null;
        try {
            node = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LogIn controller = loader.getController();
        eventBox.getChildren().add(node);


    }


}
