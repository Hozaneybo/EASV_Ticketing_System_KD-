package gui.model;

import be.BarEvent;
import be.Customer;
import bll.CustomerManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class CustomerModel {

    private ObservableList < BarEvent > allEvents;
    private ObservableList < Customer > allCustoners;
    private CustomerManager customerManager;

    public CustomerModel() throws SQLException {
        customerManager = new CustomerManager();
        allEvents = FXCollections.observableArrayList();
        allEvents.addAll(customerManager.getAllBarEvents());

        allCustoners = FXCollections.observableArrayList();
        allCustoners.addAll(customerManager.getAllCustomer());
    }

    public ObservableList < BarEvent > getObservableEvents() {
        return allEvents;
    }

    public ObservableList < Customer > getObservableCustomers() {
        return allCustoners;
    }

    public void createCustomer(String name, String email) throws Exception {
        customerManager.createCustomer(name, email);

    }

    public void refreshEventListView() throws Exception {
        //Update the view of events
        allEvents.clear();
        allEvents.setAll(customerManager.getAllBarEvents());

    }

    public void refreshCustomer() throws Exception {

        allCustoners.clear();
        allCustoners.setAll(customerManager.getAllCustomer());

    }

}