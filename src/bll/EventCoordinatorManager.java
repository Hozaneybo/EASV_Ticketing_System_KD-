package bll;

import be.*;
import dal.EventCoordinator_DB;

import java.sql.SQLException;
import java.util.List;

public class EventCoordinatorManager {

    private EventCoordinator_DB eventCoordinatorDb;

    public EventCoordinatorManager() {
        eventCoordinatorDb = new EventCoordinator_DB();
    }

    public List<BarEvent> getAllBarEvents() throws SQLException {
        return eventCoordinatorDb.getAllBarEvents();
    }


    public BarEvent createNewBarEvent(String eventName, String eventAddress, String notes, String startTime, String endTime, TicketType type, int coordinator) throws Exception {
        return eventCoordinatorDb.createBarEvent(eventName, eventAddress, notes, startTime, endTime, type, coordinator);
    }

    public void updateBarEvent(BarEvent updatedBarEvent) throws Exception {
        eventCoordinatorDb.updateBarEvent(updatedBarEvent);
    }

    public void deleteBarEvent(BarEvent barEvent) throws Exception {
        eventCoordinatorDb.deleteBarEvent(barEvent);
    }

    public int getCoordinatorId(int event_id) throws SQLException {
       return eventCoordinatorDb.getCoordinatorId(event_id);
    }

}
