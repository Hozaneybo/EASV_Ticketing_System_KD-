package bll;

import be.Admin;
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

    public Admin logIn(String username, String password) throws SQLServerException {
        return adminDb.logIn(username, password);
    }

    public EventCoordinator createNewEventCoordinator(String fullName, String username, String password) throws Exception {
        return adminDb.createNewEventCoordinator( fullName, username, password);
    }

    public List<EventCoordinator> getAllEventCoordinator() throws SQLException {
        return adminDb.getAllEventCoordinator();
    }
}
