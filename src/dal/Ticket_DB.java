package dal;

import be.*;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.database.DBConnector;
import java.sql.*;

public class Ticket_DB {

    private Ticket ticket;
    private DBConnector dbConnector;

    public Ticket_DB() {
        dbConnector = new DBConnector();
        ticket = new Ticket();

    }

    public Ticket createTicket(int event, int customer, int coordinator, String qrCode) throws Exception {

        String sql = "INSERT INTO Tickets VALUES (?,?,?,?);";
        try (Connection connection = dbConnector.getConnected()) {

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, event);
            statement.setInt(2, customer);
            statement.setInt(3, coordinator);
            statement.setString(4, qrCode);

            statement.executeUpdate();

            ResultSet result = statement.getGeneratedKeys();
            int id = 0;

            if (result.next()) {
                id = result.getInt(1);
            }

            Ticket ticket1 = new Ticket(id, event, customer, coordinator, qrCode);

            return ticket1;

        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}