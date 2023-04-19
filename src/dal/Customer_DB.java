package dal;

import be.BarEvent;
import be.Customer;
import be.TicketType;
import dal.database.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Customer_DB {
    DBConnector dbConnector;

    public Customer_DB() {
        dbConnector = new DBConnector();
    }

    public List < BarEvent > getAllBarEvents() throws SQLException {
        List < BarEvent > allBarEvents = new ArrayList < > ();

        try (Connection conn = dbConnector.getConnected()) {
            String sql = "SELECT * FROM BarEvent;";
            Statement statement = conn.createStatement();
            //Run the SQL statement
            if (statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String eventName = resultSet.getString("Event_Name");
                    String eventAddress = resultSet.getString("Event_Address");
                    String notes = resultSet.getString("Notes");
                    String startTime = resultSet.getString("Start_Time");
                    String endTime = resultSet.getString("End_Time");
                    TicketType type = TicketType.valueOf(resultSet.getString("TicketType"));
                    allBarEvents.add(new BarEvent(id, eventName, eventAddress, notes, startTime, endTime, type));
                }
            }
        }

        return allBarEvents;
    }

    public Customer createCustomer(String name, String email) throws Exception {
        // Creates an SQL command
        String sql = "INSERT INTO Customer (Name, email) VALUES (?,?);";

        // Get connection to database
        try (Connection connection = dbConnector.getConnected()) {
            // Creates a statement
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Bind parameters
            stmt.setString(1, name);
            stmt.setString(2, email);

            // Run the specified SQL statement
            stmt.executeUpdate();

            // Get the generated ID from the DB
            ResultSet result = stmt.getGeneratedKeys();
            int id = 0;

            if (result.next()) {
                id = result.getInt(1);
            }

            // Create a BarEvent object and send up the layers
            Customer customer1 = new Customer(id, name, email);
            return customer1;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not create a Customer", ex);
        }
    }

    public List < Customer > getAllCustomer() throws SQLException {
        List < Customer > allCustomers = new ArrayList < > ();

        try (Connection conn = dbConnector.getConnected()) {
            String sql = "SELECT * FROM Customer;";
            Statement statement = conn.createStatement();
            //Run the SQL statement
            if (statement.execute(sql)) {
                ResultSet result = statement.getResultSet();
                while (result.next()) {
                    int id = result.getInt("id");
                    String customerName = result.getString("Name");
                    String email = result.getString("email");

                    allCustomers.add(new Customer(id, customerName, email));
                }
            }
        }

        return allCustomers;
    }
}