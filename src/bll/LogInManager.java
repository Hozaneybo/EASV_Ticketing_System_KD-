package bll;

import be.Admin;
import be.EventCoordinator;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.LoginDAO;

public class LogInManager {

    private LoginDAO loginDAO;

    public LogInManager() {

        loginDAO = new LoginDAO();
    }

    public Admin adminLogIn(String username, String password) throws SQLServerException {
        return loginDAO.adminLogIn(username, password);
    }

    public EventCoordinator coordinatorLogIn(String username, String password) throws Exception {
        return loginDAO.coordinatorLogIn(username, password);
    }
}
