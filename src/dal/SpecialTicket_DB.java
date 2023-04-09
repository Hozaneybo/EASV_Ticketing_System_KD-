package dal;

import be.SpecialTicket;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.database.DBConnector;

import java.awt.image.BufferedImage;
import java.sql.*;

public class SpecialTicket_DB {


   private DBConnector dbConnector;

   public SpecialTicket_DB(){

       dbConnector = new DBConnector();
   }


   public SpecialTicket createSpecialTicket(String qrCode){

       String sql = "INSERT INTO SpecialTicket (QR_Code, used) VALUES (?,?);";
       try (Connection connection = dbConnector.getConnected()){
           PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

           statement.setString(1, qrCode);
           statement.setInt(2, 0);

           statement.executeUpdate();

           ResultSet result = statement.getGeneratedKeys();
           int id = 0;

           if(result.next()){
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

   /* public SpecialTicket createSpecialTicket(String qrCode){
        String sql = "INSERT INTO SpecialTicket (QR_Code, used) VALUES (?,?);";
        try (Connection connection = dbConnector.getConnected()){
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, qrCode);
            statement.setInt(2, 0);
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            int id = 0;
            if(result.next()){
                id = result.getInt(1);
            }
            SpecialTicket ticket = new SpecialTicket(id, qrCode, false);
            // Check if input value equals the QR code in database
            if (qrCodeExistsInDatabase(qrCode)) {
                // Update used column to 1
                sql = "UPDATE SpecialTicket SET used = 1 WHERE QR_Code = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, qrCode);
                statement.executeUpdate();
                // Set ticket used property to true
                ticket.setUsed(true);
            }
            return ticket;
        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean qrCodeExistsInDatabase(String qrCode) throws SQLException {
        String sql = "SELECT QR_Code FROM SpecialTicket WHERE QR_Code = ?";
        try (Connection connection = dbConnector.getConnected()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, qrCode);
            ResultSet result = statement.executeQuery();
            return result.next();
        }
    }


    public SpecialTicket createSpecialTicket(String qrCode) {

       String sqlInsert = "INSERT INTO SpecialTicket (QR_Code, used) VALUES (?,?);";
       String sqlUpdate = "UPDATE SpecialTicket SET used = 1 WHERE QR_Code = ? AND used = 0;";
       try (Connection connection = dbConnector.getConnected()) {
           // Insert the new ticket with used value of 0
           PreparedStatement insertStatement = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
           insertStatement.setString(1, qrCode);
           insertStatement.setInt(2, 0);
           insertStatement.executeUpdate();
           ResultSet result = insertStatement.getGeneratedKeys();
           int id = 0;
           if (result.next()) {
               id = result.getInt(1);
           }
           // Check if input value equals the QR code in the database and the used value is 0
           PreparedStatement selectStatement = connection.prepareStatement("SELECT used FROM SpecialTicket WHERE QR_Code = ?");
           selectStatement.setString(1, qrCode);
           ResultSet selectResult = selectStatement.executeQuery();
           boolean isUsed = false;
           if (selectResult.next()) {
               isUsed = selectResult.getBoolean("used");
           }
           // If the ticket is used, set the used property of the ticket to true
           SpecialTicket ticket = new SpecialTicket(id, qrCode, isUsed);
           if (isUsed) {
               ticket.setUsed(true);
           } else {
               // Update the used value to 1
               PreparedStatement updateStatement = connection.prepareStatement(sqlUpdate);
               updateStatement.setString(1, qrCode);
               int rowsAffected = updateStatement.executeUpdate();
               if (rowsAffected == 1) {
                   ticket.setUsed(true);
               }
           }
           return ticket;
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
   }*/




}

