
package gui.controller.TicketsControllers;

        import be.TicketType;
        import gui.model.FacadeModel;

        import javafx.embed.swing.SwingFXUtils;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.fxml.Initializable;
        import javafx.scene.Node;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.ComboBox;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextField;
        import javafx.stage.Stage;

        import java.awt.image.BufferedImage;
        import java.net.URL;
        import java.sql.SQLException;
        import java.util.ResourceBundle;

public class BuyTicketController implements Initializable {

    @FXML
    private TextField customerNameField, ticketQuantity, customerEmailField;

    @FXML
    private Label eventLocationLbl, eventNameLbl;

    @FXML
    private ComboBox<String> ticketType;


    private FacadeModel facadeModel;
    private int eventIndex;

    public void setEventIndex(int eventIndex) {
        this.eventIndex = eventIndex;
    }

    public int getEventIndex() {
        return eventIndex;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            facadeModel = new FacadeModel();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ticketType.setItems(facadeModel.getEventCoordinatorModel().getTypes());
    }

    @FXML
    void submitBuying(ActionEvent event) {

        String name = customerNameField.getText();
        String email = customerEmailField.getText();

        if (!name.isEmpty() && !email.isEmpty()) {

            try {
                facadeModel.getCustomerModel().createCustomer(name, email);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        buyMoreThanOneTicket();



        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    public void buyMoreThanOneTicket(){
        int quantity = Integer.parseInt(ticketQuantity.getText());

        if(quantity ==1) {
            showTicketsCategory();
            putTicketInDataBase();

        } else {
            for (int j = 0; j < quantity; j++) {
                showTicketsCategory();
                putTicketInDataBase();
            }
        }
    }



   private void showTicketsCategory(){

        if(ticketType.getValue().equals(TicketType.STANDARD.toString())){
       try {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/Tickets/StandardTicket.fxml"));
           Parent root = loader.load();
           StandardTicketController controller = loader.getController();

           int eventsIndex = getEventIndex();

           controller.getEventName().setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(eventsIndex).getEventName());
           controller.getEventAddress().setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(eventsIndex).getEventAddress());
           controller.getNotes().setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(eventsIndex).getNotes());
           controller.getEventStart().setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(eventsIndex).getStartTime());
           controller.getEventEnd().setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(eventsIndex).getEndTime());
           controller.getCustomerName().setText(customerNameField.getText());
           eventNameLbl.setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(eventsIndex).getEventName());
           eventLocationLbl.setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(eventsIndex).getEventAddress());

           BufferedImage qrCodeImage = facadeModel.getTicketModel().printQRCodeOnTicket();
           controller.getQr_code_image().setImage(SwingFXUtils.toFXImage(qrCodeImage, null));

           Stage stage = new Stage();
           Scene scene = new Scene(root);
           stage.setScene(scene);
           stage.setTitle("Standard Ticket");
           stage.show();

       } catch (Exception e) {
           e.printStackTrace();
       }
        } else if(ticketType.getValue().equals(TicketType.CUSTOMIZED.toString())){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/Tickets/CustomizableTicket.fxml"));
                Parent root = loader.load();
                CustomizableTicketController controller = loader.getController();

                int eventsIndex = getEventIndex();

                controller.getEventName().setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(eventsIndex).getEventName());
                controller.getEventAddress().setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(eventsIndex).getEventAddress());
                controller.getNotes().setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(eventsIndex).getNotes());
                controller.getEventStart().setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(eventsIndex).getStartTime());
                controller.getEventEnd().setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(eventsIndex).getEndTime());
                controller.getCustomerName().setText(customerNameField.getText());
                controller.getCustomerEmail().setText(customerEmailField.getText());
                eventNameLbl.setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(eventsIndex).getEventName());
                eventLocationLbl.setText(facadeModel.getEventCoordinatorModel().getObservableEvents().get(eventsIndex).getEventAddress());

                BufferedImage qrCodeImage = facadeModel.getTicketModel().printQRCodeOnTicket();
                controller.getQr_code_image().setImage(SwingFXUtils.toFXImage(qrCodeImage, null));

                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Customize Ticket");
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ticketType.getValue().equals(TicketType.SPECIAL.toString())) {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/Tickets/SpecialTicket.fxml"));
                Parent root = loader.load();
                SpecialTicketController controller = loader.getController();

                BufferedImage qrCodeImage = facadeModel.getTicketModel().printQRCodeOnTicket();
                controller.getQr_code().setImage(SwingFXUtils.toFXImage(qrCodeImage, null));

                String qrcode = facadeModel.getTicketModel().readQRCodeFromFile();

                facadeModel.getSpecialTicketModel().createSpecialTicket(qrcode);

                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Special Ticket");
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

   }


    public void putTicketInDataBase(){

            int eventsIndex = getEventIndex();

            int event_id = facadeModel.getEventCoordinatorModel().getObservableEvents().get(eventsIndex).getId();
            int coordinator_id = 3;
            String qr_code = facadeModel.getTicketModel().readQRCodeFromFile();

            try {
                facadeModel.getCustomerModel().refreshCustomer();
                int customer_id = facadeModel.getCustomerModel().getObservableCustomers().get(facadeModel.getCustomerModel().getObservableCustomers().size()-1).getId();

                facadeModel.getTicketModel().createTicket(event_id, customer_id, coordinator_id, qr_code);
            }catch (SQLException exception){
                exception.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

    }


}