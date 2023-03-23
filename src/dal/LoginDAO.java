package dal;

import be.Admin;
import be.EventCoordinator;
import dal.database.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    private DBConnector dbConnector;

    public LoginDAO()
    {
        dbConnector = new DBConnector();
    }

    public Admin adminLogIn(String username, String password) {
        String sql = "SELECT * FROM Admin WHERE username = ? AND password = ?";

        try(Connection connection = dbConnector.getConnected();
            PreparedStatement statement = connection.prepareStatement(sql)) {
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
            // Handle the exception appropriately, for example, log it or rethrow it.
            throw new RuntimeException("Error while trying to login.", e);
        }
    }

    public EventCoordinator coordinatorLogIn(String username, String password) {
        String sql = "SELECT * FROM EventCoordinator WHERE username = ? AND password = ?";

        try(Connection connection = dbConnector.getConnected();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet result = statement.executeQuery();

            if(result.next()){
                int id = result.getInt("Id");
                String fullName = result.getString("FullName");
                username = result.getString("Username");
                password = result.getString("Password");

                return  new EventCoordinator(id, fullName, username, password);

            } else{
                return null;
            }

        } catch (SQLException e) {
            // Handle the exception appropriately, for example, log it or rethrow it.
            throw new RuntimeException("Error while trying to login.", e);
        }
    }
}
