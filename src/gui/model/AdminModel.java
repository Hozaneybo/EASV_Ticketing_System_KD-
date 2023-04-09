package gui.model;

import be.BarEvent;
import be.EventCoordinator;
import bll.AdminManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;


public class AdminModel {


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

    public void createNewEventCoordinator(String fullName, String username, String password) throws Exception
    {
        EventCoordinator eventCoordinator = adminManager.createNewEventCoordinator( fullName, username, password);
        allEventCoordinator.add(eventCoordinator);
    }

    public ObservableList<BarEvent> getObservableEvents() {return allEvents;}

    public void updateEventCoordinator(EventCoordinator coordinator){
         adminManager.updateEventCoordinator(coordinator);
         allEventCoordinator.addAll(coordinator);
    }

    public void deleteEventCoordinator(EventCoordinator coordinator){
        adminManager.deleteEventCoordinator(coordinator);
        allEventCoordinator.remove(coordinator);
    }

    public void deleteEvent ( BarEvent event){
        adminManager.deleteEvent(event);
        allEvents.remove(event);
    }

}
