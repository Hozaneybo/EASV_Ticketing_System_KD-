package gui.controller.costumerControllers;

import gui.controller.TicketsControllers.BuyTicketController;
import gui.model.FacadeModel;
import gui.model.FacadeModelLoader;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerEventViewController implements Initializable {

    @FXML
    private Label endTimeLbl, eventAddressLbl, eventNameLbl, eventNotes, startTimeLbl;
    @FXML
    private Button buyTicketButton;
    private int eventIndex;

    private FacadeModelLoader facadeModelLoader;
    private FacadeModel facadeModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        facadeModelLoader = FacadeModelLoader.getInstance();
        facadeModel = facadeModelLoader.getFacadeModel();
    }

    public void setEventIndex(int eventIndex) {
        this.eventIndex = eventIndex;
        // set the index as the user data of the button
        buyTicketButton.setUserData(eventIndex);
    }

    public int getEventIndex() {
        return eventIndex;
    }

    public void buyTickets(ActionEvent actionEvent) {

        // retrieve the index from the user data of the button
        int selectedEventIndex = (int) buyTicketButton.getUserData();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/Tickets/BuyTicket.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // get the BuyTicketController and set the selected event index
        BuyTicketController buyTicketController = loader.getController();
        buyTicketController.setEventIndex(selectedEventIndex);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public Label getEndTimeLbl() {
        return endTimeLbl;
    }

    public Label getEventAddressLbl() {
        return eventAddressLbl;
    }

    public Label getEventNameLbl() {
        return eventNameLbl;
    }

    public Label getEventNotes() {
        return eventNotes;
    }

    public Label getStartTimeLbl() {
        return startTimeLbl;
    }

    public Button getBuyTicketButton() {
        return buyTicketButton;
    }

    public void setBuyTicketButton(Button buyTicketButton) {
        this.buyTicketButton = buyTicketButton;
    }

    public void deleteEvent(ActionEvent actionEvent) {

    }

}