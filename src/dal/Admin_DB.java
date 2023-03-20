package dal;

import be.Admin;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.database.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin_DB {

    private DBConnector dbConnector;

    public Admin_DB(){
        dbConnector = new DBConnector();
    }

    public Admin logIn(String username, String password) throws SQLServerException {
        String sql = "Select * FROM Admin WHERE username = ? AND password = ?";

        try(Connection connection = dbConnector.getConnected()){
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet result = statement.executeQuery();

            if(result.next()){
                int id = result.getInt("Id");
                username = result.getString("username");
                password = result.getString("password");

                return  new Admin(id, username, password);

            } else{
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
