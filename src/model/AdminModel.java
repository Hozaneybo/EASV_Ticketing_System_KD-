package model;

import be.Admin;
import bll.AdminManager;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.database.DBConnector;


public class AdminModel {


    private AdminManager adminManager;
    private DBConnector dbConnector;

    public AdminModel(){
        adminManager = new AdminManager();
        dbConnector = new DBConnector();
    }

    public Admin logIn(String username, String password) throws SQLServerException {
         return adminManager.logIn(username, password);
    }
}
