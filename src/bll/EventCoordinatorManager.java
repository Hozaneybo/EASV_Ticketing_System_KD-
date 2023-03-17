package bll;

import be.BarEvent;
import dal.EventCoordinator_DB;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class EventCoordinatorManager {

    private EventCoordinator_DB eventCoordinatorDb;

    public EventCoordinatorManager() {
        eventCoordinatorDb = new EventCoordinator_DB();
    }

    public List<BarEvent> getAllBarEvents() throws SQLException {
        return eventCoordinatorDb.getAllBarevents();
    }

    public BarEvent createNewBarEvent(String eventName, String eventAddress, String notes, String startTime, String endTime) throws Exception {
        return eventCoordinatorDb.createBarEvent(eventName, eventAddress, notes, startTime, endTime);
    }

    public void updateBarEvent(BarEvent updatedBarEvent) throws Exception {
        eventCoordinatorDb.updateBarEvent(updatedBarEvent);
    }

    public void deleteBarEvent(BarEvent barEvent) throws Exception {
        eventCoordinatorDb.deleteBarEvent(barEvent);
    }
}
