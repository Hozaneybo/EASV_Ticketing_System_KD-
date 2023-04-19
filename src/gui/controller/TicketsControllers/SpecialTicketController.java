package gui.controller.TicketsControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class SpecialTicketController implements Initializable {

    @FXML
    private ImageView qr_code;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public ImageView getQr_code() {
        return qr_code;
    }
}