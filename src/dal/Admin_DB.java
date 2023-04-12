package dal;

import be.Admin;
import be.BarEvent;
import be.EventCoordinator;
import be.TicketType;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.database.DBConnector;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Admin_DB {

    private DBConnector dbConnector;

    public Admin_DB(){
        dbConnector = new DBConnector();
    }

    public EventCoordinator createNewEventCoordinator(String fullName, String username, String password) throws Exception {

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // Creates an SQL command
        String sql = "INSERT INTO EventCoordinator (fullName, username, password) VALUES (?,?,?);";

        // Get connection to database
        try (Connection connection = dbConnector.getConnected()) {
            // Creates a statement
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Bind parameters
            stmt.setString(1, fullName);
            stmt.setString(2, username);
            stmt.setString(3, hashedPassword);

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

   /* public List<EventCoordinator> getCoordinatorByName(String name){
        List<EventCoordinator> username = new ArrayList<>();

        try (Connection connection = dbConnector.getConnected()){
            String sql = "SELECT username FROM EventCoordinator;";
            Statement statement = connection.createStatement();

            if(statement.execute(sql)){

                ResultSet resultSet = statement.getResultSet();
                while(resultSet.next()){
                    String userName = resultSet.getString("username");

                    username.add(userName);
                }
            }

        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return username;
    }*/

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
                    TicketType type = TicketType.valueOf(resultSet.getString("TicketType"));
                    allBarEvents.add(new BarEvent(id, eventName, eventAddress, notes, startTime, endTime, type));
                }
            }
        }
        return allBarEvents;
    }

    public void updateEventCoordinator(EventCoordinator coordinator) {

        String sql = "UPDATE EventCoordinator SET fullname = ? , username = ?, password = ? WHERE id = ? ";

        try (Connection connection = dbConnector.getConnected()) {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, coordinator.getFullName());
            statement.setString(2, coordinator.getUsername());
            statement.setString(3, coordinator.getPassword());
            statement.setInt(4,coordinator.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void deleteEventCoordinator(EventCoordinator coordinator){

        String sql = "DELETE FROM EventCoordinator WHERE id = ? ";
        try (Connection connection = dbConnector.getConnected()){

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, coordinator.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void deleteEvent ( BarEvent event){

        String sql = "DELETE FROM Event WHERE Id = ?";
        try (Connection connection = dbConnector.getConnected()){

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, event.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



}
