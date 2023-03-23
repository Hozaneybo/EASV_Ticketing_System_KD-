package gui.model;

import be.BarEvent;
import bll.CustomerManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class CustomerModel {

    private ObservableList<BarEvent> allEvents;
    private CustomerManager customerManager;

    public CustomerModel() throws SQLException {
        customerManager = new CustomerManager();
        allEvents = FXCollections.observableArrayList();
        allEvents.addAll(customerManager.getAllBarEvents());
    }

    public ObservableList<BarEvent> getObservableEvents(){
        return allEvents;
    }

    public void refreshEventListView() throws Exception {
        //Update the view of events
        allEvents.clear();
        allEvents.setAll(customerManager.getAllBarEvents());
    }
}
