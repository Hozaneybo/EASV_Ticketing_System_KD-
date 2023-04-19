package dal;

import be.Admin;
import be.EventCoordinator;
import dal.database.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

public class LoginDAO {
    private DBConnector dbConnector;

    public LoginDAO() {
        dbConnector = new DBConnector();
    }

    public Admin adminLogIn(String username, String password) {
        String sql = "SELECT * FROM Admin WHERE username = ? AND password = ?";

        try (Connection connection = dbConnector.getConnected(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                int id = result.getInt("Id");
                username = result.getString("Username");
                password = result.getString("Password");

                return new Admin(id, username, password);

            } else {
                return null;
            }

        } catch (SQLException e) {
            // Handle the exception appropriately, for example, log it or rethrow it.
            throw new RuntimeException("Error while trying to login.", e);
        }
    }

    public EventCoordinator coordinatorLogIn(String username, String password) throws Exception {
        // Query the database to retrieve the salt and hashed password associated with the given username
        String sql = "SELECT id, fullName, salt, password FROM EventCoordinator WHERE username = ?;";

        try (Connection connection = dbConnector.getConnected()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Extract the ID, full name, salt, and hashed password from the result set
                int id = rs.getInt("id");
                String fullName = rs.getString("fullName");
                String salt = rs.getString("salt");
                String hashedPassword = rs.getString("password");

                // Use BCrypt to check if the provided password matches the stored hashed password
                if (BCrypt.checkpw(password, hashedPassword)) {
                    // If the password is correct, create and return a new EventCoordinator object
                    return new EventCoordinator(id, fullName, username, hashedPassword);
                } else {
                    // If the password is incorrect, return null
                    return null;
                }
            } else {
                // If no user with the given username was found, return null
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not check login credentials", ex);
        }
    }

}