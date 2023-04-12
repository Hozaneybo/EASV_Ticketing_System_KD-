package gui.model;

import be.Admin;
import be.EventCoordinator;
import bll.LogInManager;
import com.microsoft.sqlserver.jdbc.SQLServerException;

public class LogInModel {

    private LogInManager logInManager;

    public LogInModel() {
        logInManager = new LogInManager();
    }

    public Admin adminLogIn(String username, String password) throws SQLServerException {
        return logInManager.adminLogIn(username, password);
    }

    public EventCoordinator coordinatorLogIn(String username, String password) throws Exception {
        return logInManager.coordinatorLogIn(username, password);
    }
}
