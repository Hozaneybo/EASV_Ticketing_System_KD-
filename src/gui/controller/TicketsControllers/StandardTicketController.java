package gui.controller.TicketsControllers;


import gui.model.FacadeModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;


import java.net.URL;
import java.util.ResourceBundle;

public class StandardTicketController implements Initializable {

    @FXML
    private Label customerName, eventAddress, eventEnd, eventName, eventStart, notes ;
    @FXML
    private ImageView qr_code_image;
    @FXML
    private FacadeModel facadeModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try{
            facadeModel = new FacadeModel();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public Label getCustomerName() {
        return customerName;
    }

    public Label getEventAddress() {
        return eventAddress;
    }

    public Label getEventEnd() {
        return eventEnd;
    }

    public Label getEventName() {
        return eventName;
    }

    public Label getEventStart() {
        return eventStart;
    }

    public Label getNotes() {
        return notes;
    }

    public ImageView getQr_code_image() {
        return qr_code_image;
    }
}
