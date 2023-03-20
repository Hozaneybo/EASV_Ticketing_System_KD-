package gui.model;

import be.Admin;
import be.EventCoordinator;
import bll.AdminManager;
import bll.EventCoordinatorManager;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.database.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class AdminModel {


    private AdminManager adminManager;
    private ObservableList<EventCoordinator> allEventCoordinator;


    DBConnector dbConnector;

    public AdminModel() throws SQLException {
        adminManager = new AdminManager();
        dbConnector = new DBConnector();

        allEventCoordinator = FXCollections.observableArrayList();
        allEventCoordinator.addAll(adminManager.getAllEventCoordinator());
    }
    public ObservableList<EventCoordinator> getObservableEventCoordinator() {return allEventCoordinator;}
    public Admin logIn(String username, String password) throws SQLServerException {
         return adminManager.logIn(username, password);
    }
    public void createNewEventCoordinator(String fullName, String username, String password) throws Exception
    {
        EventCoordinator eventCoordinator = adminManager.createNewEventCoordinator( fullName, username, password);
        allEventCoordinator.add(eventCoordinator);
    }
}
