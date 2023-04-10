package dal;
import be.*;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.database.DBConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class EventCoordinator_DB {
    DBConnector dbConnector;
    private Ticket ticket;
    public EventCoordinator_DB() {
        dbConnector = new DBConnector();
    }
    public List < BarEvent > getAllBarEvents() throws SQLException {
        List < BarEvent > allBarEvents = new ArrayList < > ();
        try (Connection conn = dbConnector.getConnected()) {
            String sql = "SELECT * FROM BarEvent;";
            Statement statement = conn.createStatement();
            //Run the SQL statementgti
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
    public BarEvent createBarEvent(String eventName, String eventAddress, String notes, String startTime, String endTime, TicketType type, int coordinator) throws Exception {
        // Creates an SQL command
        String sql = "INSERT INTO BarEvent (Event_Name, Event_Address, Notes, Start_Time, End_Time, TicketType, Coordinator_id) VALUES (?,?,?,?,?,?,?);";
        // Get connection to database
        try (Connection connection = dbConnector.getConnected()) {
            // Creates a statement
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // Bind parameters
            stmt.setString(1, eventName);
            stmt.setString(2, eventAddress);
            stmt.setString(3, notes);
            stmt.setString(4, startTime);
            stmt.setString(5, endTime);
            stmt.setString(6, String.valueOf(type));
            stmt.setInt(7, coordinator);
            // Run the specified SQL statement
            stmt.executeUpdate();
            // Get the generated ID from the DB
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            // Create a BarEvent object and send up the layers
            BarEvent barEvent = new BarEvent(id, eventName, eventAddress, notes, startTime, endTime, type, coordinator);
            return barEvent;
        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // Update an existing BarEvent object in the database
    public void updateBarEvent(BarEvent event) throws Exception {
        try (Connection conn = dbConnector.getConnected()) {
            String sql = "UPDATE BarEvent SET Event_Name = ?, Event_Address = ?, Notes = ?, Start_Time = ?, End_Time = ?, TicketType = ? WHERE ID = ?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, event.getEventName());
            stmt.setString(2, event.getEventAddress());
            stmt.setString(3, event.getNotes());
            stmt.setString(4, event.getStartTime());
            stmt.setString(5, event.getEndTime());
            stmt.setString(6, String.valueOf(event.getType()));
            stmt.setInt(7, event.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not update BarEvent", ex);
        }
    }
    public void deleteBarEvent(BarEvent barEvent) throws Exception {
        //Get connection to database
        try (Connection conn = dbConnector.getConnected()) {
            //Create an SQL command
            String sql = "DELETE FROM BarEvent WHERE ID = (?);";
            //Create a statement
            PreparedStatement stmt = conn.prepareStatement(sql);
            // Bind parameters
            stmt.setInt(1, barEvent.getId());
            //Run the SQL statement
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception(ex);
        }
    }
    public int getCoordinatorId(int event_id) throws SQLException {
        int coordinatorId = 0;
        try (Connection conn = dbConnector.getConnected()) {
            String sql = "SELECT Coordinator_id FROM BarEvent WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, event_id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                coordinatorId = resultSet.getInt("Coordinator_id");
            }
        }
        return coordinatorId;
    }
}