package gui.model;

import java.sql.SQLException;

public class FacadeModel {

    private EventCoordinatorModel eventCoordinatorModel;
    private TicketModel ticketModel;



    public FacadeModel() throws SQLException {

        this.eventCoordinatorModel = new EventCoordinatorModel();
        this.ticketModel = new TicketModel();
    }

    public EventCoordinatorModel getEventCoordinatorModel() {
        return eventCoordinatorModel;
    }

    public TicketModel getTicketModel() {
        return ticketModel;
    }
}
