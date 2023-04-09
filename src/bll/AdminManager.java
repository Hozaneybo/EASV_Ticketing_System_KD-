package bll;


import be.BarEvent;
import be.EventCoordinator;
import dal.Admin_DB;

import java.sql.SQLException;
import java.util.List;

public class AdminManager {


    private Admin_DB adminDb;


    public AdminManager(){
        adminDb = new Admin_DB();
    }


    public EventCoordinator createNewEventCoordinator(String fullName, String username, String password) throws Exception {
        return adminDb.createNewEventCoordinator( fullName, username, password);
    }

    public List<EventCoordinator> getAllEventCoordinators() throws SQLException {
        return adminDb.getAllEventCoordinators();
    }

    public List<BarEvent> getAllBarEvents() throws SQLException {
        return adminDb.getAllBarEvents();
    }

    public void updateEventCoordinator(EventCoordinator coordinator){
        adminDb.updateEventCoordinator(coordinator);
    }

    public void deleteEventCoordinator(EventCoordinator coordinator){
        adminDb.deleteEventCoordinator(coordinator);
    }

    public void deleteEvent ( BarEvent event){
        adminDb.deleteEvent(event);
    }
}
