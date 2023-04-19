package be;
import java.util.ArrayList;
import java.util.List;
public class BarEvent {
    private int id, coordinator_id;
    private String eventName, eventAddress, notes, startTime, endTime;
    private TicketType type;
    public BarEvent(int id, String eventName, String eventAddress, String notes, String startTime, String endTime, TicketType type, int coordinator_id) {
        this.id = id;
        this.eventName = eventName;
        this.eventAddress = eventAddress;
        this.notes = notes;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.coordinator_id = coordinator_id;
    }
    public BarEvent(int id, String eventName, String eventAddress, String notes, String startTime, String endTime, TicketType type) {
        this.id = id;
        this.eventName = eventName;
        this.eventAddress = eventAddress;
        this.notes = notes;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
    }
    public BarEvent(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public String getEventAddress() {
        return eventAddress;
    }
    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public TicketType getType() {
        return type;
    }
    public void setType(TicketType type) {
        this.type = type;
    }
    public int getCoordinator_id() {
        return coordinator_id;
    }
    public void setCoordinator_id(int coordinator_id) {
        this.coordinator_id = coordinator_id;
    }
    @Override
    public String toString() {
        return "BarEvent{" +
                "ID: " + id +
                ", eventName: '" + eventName + '\'' +
                ", eventAddress: '" + eventAddress + '\'' +
                ", notes: '" + notes + '\'' +
                ", startTime: '" + startTime + '\'' +
                ", endTime: '" + endTime + '\'' +
                '}';
    }
}