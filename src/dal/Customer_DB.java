package dal;

import be.BarEvent;
import dal.database.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Customer_DB {
    DBConnector dbConnector;

    public Customer_DB() {
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
}
