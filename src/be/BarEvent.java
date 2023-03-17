package be;

import java.time.LocalDateTime;

public class BarEvent {

    private int id;

    private String eventName, eventAddress, notes, startTime, endTime;

    public BarEvent(int id, String eventName, String eventAddress, String notes, String startTime, String endTime) {
        this.id = id;
        this.eventName = eventName;
        this.eventAddress = eventAddress;
        this.notes = notes;
        this.startTime = startTime;
        this.endTime = endTime;
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
