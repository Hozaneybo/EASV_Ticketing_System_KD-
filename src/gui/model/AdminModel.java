package gui.model;

import be.Admin;
import be.BarEvent;
import be.EventCoordinator;
import bll.AdminManager;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;


public class AdminModel{


    private AdminManager adminManager;

    private ObservableList<EventCoordinator> allEventCoordinator;
    private ObservableList<BarEvent> allEvents;



    public AdminModel() throws SQLException {
        adminManager = new AdminManager();
        allEventCoordinator = FXCollections.observableArrayList();
        allEvents = FXCollections.observableArrayList();
        allEvents.addAll(adminManager.getAllBarEvents());
        allEventCoordinator.addAll(adminManager.getAllEventCoordinators());
    }
    public ObservableList<EventCoordinator> getObservableEventCoordinator() {return allEventCoordinator;}
   /* public Admin logIn(String username, String password) throws SQLServerException {
        return adminManager.logIn(username, password);
    }*/
    public void createNewEventCoordinator(String fullName, String username, String password) throws Exception
    {
        EventCoordinator eventCoordinator = adminManager.createNewEventCoordinator( fullName, username, password);
        allEventCoordinator.add(eventCoordinator);
    }

    public ObservableList<BarEvent> getObservableEvents() {return allEvents;}
}
