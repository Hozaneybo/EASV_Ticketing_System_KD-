package dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnector {

    private SQLServerDataSource dataSource;

    public DBConnector(){

        dataSource = new SQLServerDataSource();
        dataSource.setServerName("10.176.111.31");
        dataSource.setDatabaseName("Event_Tickets_EASV_bar");
        dataSource.setUser("CSe22A_23");
        dataSource.setPassword("DataMatiker");
        dataSource.setTrustServerCertificate(true);
        dataSource.setPortNumber(1433);
    }

    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }

   /* public static void main(String[] args) throws SQLServerException {
        DBConnector dbConnector = new DBConnector();

        try(Connection connection = dbConnector.getConnection()){
            if(!connection.isClosed()){
                System.out.println("you have access to your database" + !connection.isClosed());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

}


