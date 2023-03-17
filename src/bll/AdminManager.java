package bll;

import be.Admin;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.Admin_DB;

public class AdminManager {

    private Admin_DB adminDb;


    public AdminManager(){
        adminDb = new Admin_DB();
    }

    public Admin logIn(String username, String password) throws SQLServerException {
        return adminDb.logIn(username, password);
    }
}
