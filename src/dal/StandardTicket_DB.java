package dal;

import be.BarEvent;
import be.EventCoordinator;
import be.Ticket;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.database.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StandardTicket_DB {

    private Ticket ticket;
    private DBConnector dbConnector;
    private Ticket.ObjectOfTicket ticketObject;


    public StandardTicket_DB() {
        dbConnector = new DBConnector();
        ticket = new Ticket();
        ticketObject = ticket.new ObjectOfTicket();

    }

    public EventCoordinator ticketEventInfo(EventCoordinator eventCoordinator) throws SQLServerException {

        String sql = "SELECT * FROM BarEvent;";
        try(Connection connection = dbConnector.getConnected()){

            Statement statement = connection.createStatement();
            if(statement.execute(sql)){

            }
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next())
                {
                    int id = resultSet.getInt("ID");
                    String eventName = resultSet.getString("Event_Name");
                    String eventAddress = resultSet.getString("Event_Address");
                    String notes = resultSet.getString("Notes");
                    String startTime = resultSet.getString("Start_Time");
                    String endTime = resultSet.getString("End_Time");
                    allBarEvents.add(new BarEvent(id, eventName, eventAddress, notes, startTime, endTime));
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
            return
    }
}
