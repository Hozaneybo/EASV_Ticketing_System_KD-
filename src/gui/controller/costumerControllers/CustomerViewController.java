package gui.controller.costumerControllers;

import gui.model.FacadeModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerViewController implements Initializable {

    @FXML
    private VBox eventBox;

    private FacadeModel facadeModel;

    int eventsNumber;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       try{
          facadeModel = new FacadeModel();

       }catch (Exception e){
           e.printStackTrace();
       }

    }

    @FXML
    private void showAllEvents() {
        eventBox.getChildren().clear();
        try {
            facadeModel.getCustomerModel().refreshEventListView();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        eventsNumber = facadeModel.getCustomerModel().getObservableEvents().size();

        if ( eventsNumber != 0) {
            for (int i = 0; i < eventsNumber; i++) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/customerGUI/CustomerEventView.fxml"));
                    Node node = loader.load();

                    CustomerEventViewController controller = loader.getController();

                    // set the index of the event in the button
                   controller.getBuyTicketButton().setUserData(i);

                    controller.setEventIndex(i);

                    controller.getEventNameLbl().setText(facadeModel.getCustomerModel().getObservableEvents().get(i).getEventName());
                    controller.getEventAddressLbl().setText(facadeModel.getCustomerModel().getObservableEvents().get(i).getEventAddress());
                    controller.getEventNotes().setText(facadeModel.getCustomerModel().getObservableEvents().get(i).getNotes());
                    controller.getStartTimeLbl().setText(facadeModel.getCustomerModel().getObservableEvents().get(i).getStartTime());
                    controller.getEndTimeLbl().setText(facadeModel.getCustomerModel().getObservableEvents().get(i).getEndTime());

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

            e.printStackTrace();
        }
        eventBox.getChildren().add(node);


    }

    }
