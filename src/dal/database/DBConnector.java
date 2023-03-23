package dal.database;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnector {

    SQLServerDataSource dataSource;

    //Defining server and database
    public DBConnector() {
        dataSource = new SQLServerDataSource();
        dataSource.setServerName("10.176.111.34");
        dataSource.setServerName("*****");
        dataSource.setDatabaseName("******");
        dataSource.setUser("******"); // Type your given username
        dataSource.setPassword("*******"); // Type your given password
        dataSource.setTrustServerCertificate(true);
        dataSource.setPortNumber(1433);


    }

    public Connection getConnected() throws SQLServerException {
        return dataSource.getConnection();
    }


    //Only to see if we have connection or not
    public static void main(String[] args) throws SQLServerException {
        DBConnector dbConnector = new DBConnector();
        try(Connection connection =dbConnector.getConnected())
        {
            if(!connection.isClosed())
            {
                System.out.println("Is it opened..?\n" + !connection.isClosed());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
