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

          /*public ArrayList<Ticket> getAllTickets() throws SQLException {
        //Create and return songs
        ArrayList<Ticket> tickets = new ArrayList<>();

        //Get connection to database
        try (Connection connection = dbConnector.getConnected()) {
            //Create an SQL command
            String sql = "SELECT * FROM BarEvent e, Customer c, EventCoordinator co, tickets t " +
                    "WHERE e.id = t.event_id AND c.id = t.customer_id AND co.id = t.coordinator_id;";

            //Create some statements
            Statement statement = connection.createStatement();

            //Do what you suppose to do
            if (statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    int id = resultSet.getInt("ticket_id");
                    int event_id = resultSet.getInt("even_id");
                    int customer_id = resultSet.getInt("customer_id");
                    int coordinatoe_id = resultSet.getInt("coordinator_id");
                    String qr_code = resultSet.getString("qr_code");

                    Ticket ticket1 = new Ticket(id, event_id, customer_id, coordinatoe_id, qr_code);
                    tickets.add(ticket1);
                }
            }
        }
        return tickets;
    }*/


}





