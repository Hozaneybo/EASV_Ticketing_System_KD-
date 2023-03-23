package bll;

import be.Admin;
import be.BarEvent;
import be.EventCoordinator;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.Admin_DB;
import dal.EventCoordinator_DB;

import java.sql.SQLException;
import java.util.List;

public class AdminManager {


    private Admin_DB adminDb;


    public AdminManager(){
        adminDb = new Admin_DB();
    }

    /*public Admin logIn(String username, String password) throws SQLServerException {
        return adminDb.alogIn(username, password);
    }*/

    public EventCoordinator createNewEventCoordinator(String fullName, String username, String password) throws Exception {
        return adminDb.createNewEventCoordinator( fullName, username, password);
    }

    public List<EventCoordinator> getAllEventCoordinators() throws SQLException {
        return adminDb.getAllEventCoordinators();
    }

    public List<BarEvent> getAllBarEvents() throws SQLException {
        return adminDb.getAllBarEvents();
    }
}
