package gui.model;

import java.sql.SQLException;

public class FacadeModel {

    private EventCoordinatorModel eventCoordinatorModel;
    private TicketModel ticketModel;
    private CustomerModel customerModel;
    private AdminModel adminModel;
    private LogInModel logInModel;
    private SpecialTicketModel specialTicketModel;




    public FacadeModel() throws SQLException {

        eventCoordinatorModel = new EventCoordinatorModel();
        ticketModel = new TicketModel();
        customerModel = new CustomerModel();
        adminModel = new AdminModel();
        logInModel = new LogInModel();
        specialTicketModel = new SpecialTicketModel();
    }

    public EventCoordinatorModel getEventCoordinatorModel() {
        return eventCoordinatorModel;
    }

    public TicketModel getTicketModel() {
        return ticketModel;
    }

    public CustomerModel getCustomerModel() {
        return customerModel;
    }

    public AdminModel getAdminModel() {
        return adminModel;
    }

    public LogInModel getLogInModel() {
        return logInModel;
    }
    public SpecialTicketModel getSpecialTicketModel(){
        return specialTicketModel;
    }
}