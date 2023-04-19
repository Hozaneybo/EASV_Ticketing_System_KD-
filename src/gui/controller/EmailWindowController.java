package gui.controller;

import gui.model.AppServicesModel;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void attach(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose File");
        selectedFile = fileChooser.showOpenDialog(null);
    }

    @FXML
    void sendEmail(ActionEvent event) {
        // Validate receiver email
        String receiverEmail = receiver.getText();
        if (!receiverEmail.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter a valid email address for the receiver.");
            alert.showAndWait();
            return;
        }

// Validate subject
        String emailSubject = subject.getText();
        if (!emailSubject.matches("[a-zA-Z0-9]{1,15}")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter a subject that contains only letters and numbers, and is between 1 and 15 characters.");
            alert.showAndWait();
            return;
        }

// Check for empty fields and attached file
        if (receiverEmail.isEmpty() || emailSubject.isEmpty() || message.getText().isEmpty() || selectedFile == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please fill in all fields and attach a file before sending the email.");
            alert.showAndWait();
            return;
        }

// Send email
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() {
                String messageToBeSent = message.getText();
                String filePath = selectedFile.getAbsolutePath();
                String fileName = selectedFile.getName();
                try {
                    appServicesModel.sendEmail(receiverEmail, emailSubject, messageToBeSent, filePath, fileName);
                } catch (Exception e) {
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