package dal;

import be.SpecialTicket;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.database.DBConnector;

import java.sql.*;

public class SpecialTicket_DB {

    private DBConnector dbConnector;

    public SpecialTicket_DB() {

        dbConnector = new DBConnector();
    }

    public SpecialTicket createSpecialTicket(String qrCode) {

        String sql = "INSERT INTO SpecialTicket (QR_Code, used) VALUES (?,?);";
        try (Connection connection = dbConnector.getConnected()) {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, qrCode);
            statement.setInt(2, 0);

            statement.executeUpdate();

            ResultSet result = statement.getGeneratedKeys();
            int id = 0;

            if (result.next()) {
                id = result.getInt(1);
            }

            SpecialTicket ticket1 = new SpecialTicket(id, qrCode, false);

            return ticket1;

        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}