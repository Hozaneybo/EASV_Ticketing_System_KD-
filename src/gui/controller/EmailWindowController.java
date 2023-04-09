package gui.controller;

import gui.model.AppServicesModel;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class EmailWindowController implements Initializable {

    @FXML
    private TextArea message;

    @FXML
    private TextField receiver, subject;

    private AppServicesModel appServicesModel;

    private File selectedFile;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            appServicesModel = new AppServicesModel();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    void attach(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose File");
        selectedFile = fileChooser.showOpenDialog(null);
       /* if (selectedFile != null) {
            filePathLabel.setText(selectedFile.getAbsolutePath());
        }*/

    }

    @FXML
    void sendEmail(ActionEvent event) {

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                String receiverEmail = receiver.getText();
                String emailSubject = subject.getText();
                String messageToBeSent = message.getText();
                String filePath = selectedFile.getAbsolutePath();
                String fileName = selectedFile.getName();

                try {
                    appServicesModel.sendEmail(receiverEmail, emailSubject, messageToBeSent, filePath, fileName);
                }catch (Exception e){
                    e.printStackTrace();
                }

                return null;
            }
        };

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();

    }


}
