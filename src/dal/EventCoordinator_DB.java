package dal;

import be.BarEvent;
import be.EventCoordinator;
import dal.database.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventCoordinator_DB {

    DBConnector dbConnector;

    public EventCoordinator_DB() {
        dbConnector = new DBConnector();
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






    public BarEvent createBarEvent(String eventName, String eventAddress, String notes, String startTime, String endTime) throws Exception {
        // Creates an SQL command
        String sql = "INSERT INTO BarEvent (Event_Name, Event_Address, Notes, Start_Time, End_Time) VALUES (?,?,?,?,?);";

        // Get connection to database
        try (Connection connection = dbConnector.getConnected()) {
            // Creates a statement
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Bind parameters
            stmt.setString(1,eventName);
            stmt.setString(2, eventAddress);
            stmt.setString(3, notes);
            stmt.setString(4, startTime);
            stmt.setString(5, endTime);

            // Run the specified SQL statement
            stmt.executeUpdate();

            // Get the generated ID from the DB
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;


            if (rs.next()) {
                id = rs.getInt(1);
            }

            // Create a BarEvent object and send up the layers
            BarEvent barEvent = new BarEvent(id, eventName, eventAddress, notes, startTime, endTime);
            return barEvent;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not create a BarEvent", ex);
        }

    }

    // Update an existing BarEvent object in the database
    public void updateBarEvent(BarEvent event) throws Exception {
        try (Connection conn = dbConnector.getConnected()){
             String sql = "UPDATE BarEvent SET Event_Name = ?, Event_Address = ?, Notes = ?, Start_Time = ?, End_Time = ? WHERE ID = ?;";
             PreparedStatement stmt = conn.prepareStatement(sql);
             stmt.setString(1, event.getEventName());
             stmt.setString(2, event.getEventAddress());
             stmt.setString(3, event.getNotes());
             stmt.setString(4, event.getStartTime());
             stmt.setString(5, event.getEndTime());
             stmt.setInt(6, event.getId());
             stmt.executeUpdate();
        }
        catch (SQLException ex) {
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
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception( ex);
        }

    }

}
