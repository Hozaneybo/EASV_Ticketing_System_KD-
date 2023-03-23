package bll;

import be.BarEvent;
import dal.Customer_DB;

import java.sql.SQLException;
import java.util.List;

public class CustomerManager {

    private Customer_DB customer_Db;

    public CustomerManager() {
        customer_Db = new Customer_DB();

    }
    public List<BarEvent> getAllBarEvents() throws SQLException {
        return customer_Db.getAllBarEvents();
    }
}
