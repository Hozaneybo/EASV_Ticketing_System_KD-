package bll;

import be.BarEvent;
import be.Customer;
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
    public Customer createCustomer(String name, String email) throws Exception {
        return customer_Db.createCustomer(name, email);
    }

    public List<Customer> getAllCustomer() throws SQLException {
        return customer_Db.getAllCustomer();
    }
}
