package gui.model;

import bll.AppServicesManager;

import javax.mail.MessagingException;

public class AppServicesModel {

    private AppServicesManager appServicesManager;

    public AppServicesModel() {
        appServicesManager = new AppServicesManager();
    }


    public void sendEmail(String toEmail, String messageSubject, String yourTextMessage, String filePath, String fileName) throws MessagingException {
        appServicesManager.sendEmail(toEmail, messageSubject, yourTextMessage, filePath, fileName);
    }
}
