package dal;

import be.Admin;
import be.BarEvent;
import be.EventCoordinator;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.database.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Admin_DB {

    private DBConnector dbConnector;

    public Admin_DB(){
        dbConnector = new DBConnector();
    }

    /*public Admin logIn(String username, String password) throws SQLServerException {
        String sql = "Select * FROM Admin WHERE username = ? AND password = ?";

        try(Connection connection = dbConnector.getConnected()){
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet result = statement.executeQuery();

            if(result.next()){
                int id = result.getInt("Id");
                username = result.getString("Username");
                password = result.getString("Password");

                return  new Admin(id, username, password);

            } else{
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

    public EventCoordinator createNewEventCoordinator(String fullName, String username, String password) throws Exception {
        // Creates an SQL command
        String sql = "INSERT INTO EventCoordinator (fullName, username, password) VALUES (?,?,?);";

        // Get connection to database
        try (Connection connection = dbConnector.getConnected()) {
            // Creates a statement
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Bind parameters
            stmt.setString(1, fullName);
            stmt.setString(2, username);
            stmt.setString(3, password);

            // Run the specified SQL statement
            stmt.executeUpdate();

            // Get the generated ID from the DB
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            // Create an EventCoordinator object and send up the layers
            EventCoordinator eventCoordinator = new EventCoordinator(id, fullName, username, password);
            return eventCoordinator;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not create an EventCoordinator", ex);
        }
    }
    public List<EventCoordinator> getAllEventCoordinators() throws SQLException {
        List<EventCoordinator> allEventCoordinators = new ArrayList<>();

        try (Connection conn = dbConnector.getConnected()) {
            String sql = "SELECT * FROM EventCoordinator;";
            Statement statement = conn.createStatement();
            //Run the SQL statement
            if(statement.execute(sql))
            {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next())
                {
                    int id = resultSet.getInt("id");
                    String fullName = resultSet.getString("fullName");
                    String userName = resultSet.getString("username");
                    String password = resultSet.getString("password");

                    allEventCoordinators.add(new EventCoordinator(id, fullName, userName, password));
                }
            }
        }

        return allEventCoordinators;
    }

    public List<BarEvent> getAllBarEvents() throws SQLException {
        List<BarEvent> allBarEvents = new ArrayList<>();

        try (Connection conn = dbConnector.getConnected()) {
            String sql = "SELECT * FROM BarEvent;";
            Statement statement = conn.createStatement();
            //Run the SQL statement
            if(statement.execute(sql))
            {
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
            }
        }

        return allBarEvents;
    }

}
