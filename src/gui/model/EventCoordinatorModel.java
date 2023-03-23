package gui.model;

import be.BarEvent;
import bll.EventCoordinatorManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class EventCoordinatorModel {

    private ObservableList<BarEvent> allEvents;

    private EventCoordinatorManager eventCoordinatorManager;

    public EventCoordinatorModel() throws SQLException {

        eventCoordinatorManager = new EventCoordinatorManager();

        allEvents = FXCollections.observableArrayList();
        allEvents.addAll(eventCoordinatorManager.getAllBarEvents());
    }
    public ObservableList<BarEvent> getObservableEvents() {return allEvents;}

    public void createNewBarEvent(String eventName, String eventAddress, String notes, String startTime, String endTime) throws Exception {
        BarEvent barEvent = eventCoordinatorManager.createNewBarEvent(eventName, eventAddress, notes, startTime, endTime);
        allEvents.add(barEvent);
        refreshEventListView();

    }

    public void deleteBarEvent(BarEvent deletedBarEvent) throws Exception {
        eventCoordinatorManager.deleteBarEvent(deletedBarEvent);
    }

    public void updateBarEvent(BarEvent barEvent) throws Exception {
        eventCoordinatorManager.updateBarEvent(barEvent);
        refreshEventListView();
    }

    public void refreshEventListView() throws Exception {
        //Update the listview
        allEvents.clear();
        allEvents.setAll(eventCoordinatorManager.getAllBarEvents());
    }

}
