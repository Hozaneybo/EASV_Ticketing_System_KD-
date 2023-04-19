package gui.model;

import be.*;
import bll.EventCoordinatorManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class EventCoordinatorModel {

    private ObservableList < BarEvent > allEvents;
    private ObservableList < Ticket > allTickets;

    private EventCoordinatorManager eventCoordinatorManager;

    public EventCoordinatorModel() throws SQLException {

        eventCoordinatorManager = new EventCoordinatorManager();

        allEvents = FXCollections.observableArrayList();
        allEvents.addAll(eventCoordinatorManager.getAllBarEvents());
        allTickets = FXCollections.observableArrayList();
    }
    public ObservableList < BarEvent > getObservableEvents() {
        return allEvents;
    }

    public void createNewBarEvent(String eventName, String eventAddress, String notes, String startTime, String endTime, TicketType type, int coordinator) throws Exception {
        BarEvent barEvent = eventCoordinatorManager.createNewBarEvent(eventName, eventAddress, notes, startTime, endTime, type, coordinator);
        allEvents.add(barEvent);
        refreshEventListView();

    }

    public void deleteBarEvent(BarEvent deletedBarEvent) throws Exception {
        eventCoordinatorManager.deleteBarEvent(deletedBarEvent);
    }

    public void updateBarEvent(BarEvent event) throws Exception {
        eventCoordinatorManager.updateBarEvent(event);
        refreshEventListView();
    }

    public void refreshEventListView() throws Exception {
        //Update the listview
        allEvents.clear();
        allEvents.setAll(eventCoordinatorManager.getAllBarEvents());
    }

    public ObservableList < String > getTypes() {
        ObservableList < String > ticketTypes = FXCollections.observableArrayList();
        for (TicketType type: TicketType.values()) {
            ticketTypes.add(type.toString());
        }
        return ticketTypes;

    }
    public int getCoordinatorId(int event_id) throws SQLException {
        return eventCoordinatorManager.getCoordinatorId(event_id);
    }

}