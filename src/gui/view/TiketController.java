package gui.view;

import dal.EventCoordinator_DB;
import dal.StandardTicket_DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class TiketController  implements Initializable {


    public Label barEventLabel;
    public Label ticketTypeLabel;
    public Label customerLabel;
    public Label qrCodeLabel;
    @FXML
    private TextField email;

    @FXML
    private TextField eventName;

    @FXML
    private TextField location;

    @FXML
    private TextField name;

    @FXML
    private ImageView qrCode;

    StandardTicket_DB standardTicket = new StandardTicket_DB();
    EventCoordinator_DB eventCoordinatorDb = new EventCoordinator_DB();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }



    public void handleClick(ActionEvent actionEvent) {
    }
}
