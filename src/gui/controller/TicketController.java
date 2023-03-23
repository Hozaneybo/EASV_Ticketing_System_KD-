
package gui.controller;

        import be.TicketType;
        import gui.model.FacadeModel;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.control.ComboBox;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextField;
        import javafx.scene.image.ImageView;

        import java.net.URL;
        import java.sql.SQLException;
        import java.util.ResourceBundle;

public class TicketController implements Initializable {

    @FXML
    private TextField customerNameField, ticketQuantity, customerEmailField;

    @FXML
    private Label eventLocationLbl, eventNameLbl;

    @FXML
    private ImageView qrCode;

    @FXML
    private ComboBox<String> ticketType;


    private FacadeModel facadeModel;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            facadeModel = new FacadeModel();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ticketType.setItems(FXCollections.observableArrayList(facadeModel.getTicketModel().getTypes()));


    }
    @FXML
    void submitBuying(ActionEvent event) {

        /*if(StandardTiket.getText().equals("STANDARD")){
            BufferedImage qrCodeImage = facadeModel.getTicketModel().printQRCodeOnTicket();
            qrCode.setImage(SwingFXUtils.toFXImage(qrCodeImage, null));
            eventName.setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(0).getEventName());
            location.setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(0).getEventAddress());
        }else {
            BufferedImage qrCodeImage = facadeModel.getTicketModel().printQRCodeOnTicket();
            qrCode.setImage(SwingFXUtils.toFXImage(qrCodeImage, null));
            }*/
    }
}

