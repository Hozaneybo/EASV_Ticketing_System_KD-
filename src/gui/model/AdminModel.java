package gui.model;

import be.BarEvent;
import be.EventCoordinator;
import bll.AdminManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class AdminModel {

    private AdminManager adminManager;

    private ObservableList < EventCoordinator > allEventCoordinator;
    private ObservableList < BarEvent > allEvents;

    public AdminModel() throws SQLException {
        adminManager = new AdminManager();
        allEventCoordinator = FXCollections.observableArrayList();
        allEvents = FXCollections.observableArrayList();
        allEvents.addAll(adminManager.getAllBarEvents());
        allEventCoordinator.addAll(adminManager.getAllEventCoordinators());
        refreshEventListView();
    }
    public ObservableList < EventCoordinator > getObservableEventCoordinator() {
        return allEventCoordinator;
    }

    public void createNewEventCoordinator(String fullName, String username, String password) throws Exception {
        EventCoordinator eventCoordinator = adminManager.createNewEventCoordinator(fullName, username, password);
        allEventCoordinator.add(eventCoordinator);
        refreshEventListView();
    }

    public ObservableList < BarEvent > getObservableEvents() {
        return allEvents;
    }

    public void updateEventCoordinator(EventCoordinator coordinator) throws Exception {
        adminManager.updateEventCoordinator(coordinator);
        refreshEventListView();
    }

    public void deleteEventCoordinator(EventCoordinator coordinator) throws SQLException {
        adminManager.deleteEventCoordinator(coordinator);
        allEventCoordinator.remove(coordinator);
        refreshEventListView();
    }

    public void deleteEvent(BarEvent event) throws SQLException {
        adminManager.deleteEvent(event);
        allEvents.remove(event);
        refreshEventListView();
    }

    public void refreshEventListView() throws SQLException {
        //Update the listview
        allEvents.clear();
        allEventCoordinator.clear();
        allEvents.setAll(adminManager.getAllBarEvents());
        allEventCoordinator.setAll(adminManager.getAllEventCoordinators());
    }

}