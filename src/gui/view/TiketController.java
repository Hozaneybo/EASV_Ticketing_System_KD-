package gui.view;


import be.TicketType;
import gui.model.FacadeModel;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TiketController  implements Initializable {

    public TextField StandardTiket;
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


    private FacadeModel facadeModel;


    public TiketController() throws SQLException {
        this.facadeModel = new FacadeModel();
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }



    public void handleClick(ActionEvent actionEvent) {

        if(StandardTiket.getText().equals("STANDARD")){
            BufferedImage qrCodeImage = facadeModel.getTicketModel().printQRCodeOnTicket();
            qrCode.setImage(SwingFXUtils.toFXImage(qrCodeImage, null));
            eventName.setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(0).getEventName());
            location.setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(0).getEventAddress());
        }else {
            BufferedImage qrCodeImage = facadeModel.getTicketModel().printQRCodeOnTicket();
            qrCode.setImage(SwingFXUtils.toFXImage(qrCodeImage, null));
        }


    }
}
