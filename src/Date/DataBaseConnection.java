package Date;

import Backend.User;

import javax.swing.*;
import java.sql.*;

public class DataBaseConnection extends config {
    Connection dbConnection;

    public Connection getDbConnection() throws SQLException, ClassNotFoundException {

        String connectionString = "jdbc:mysql://" +dbHost +":" + dbPort+ "/" +dbName;

        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString,dbUser,dbPassword);
        return dbConnection;
    }

    public void addUserDB (User user) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO "+ cons.USER_TABLE + "(" +
                cons.USER_NAME + "," + cons.USER_SURNAME + "," +
                cons.USER_BIRTHDAY + "," + cons.USER_GENDER + ","+
                cons.USER_USERNAME + "," + cons.USER_PASSWORD + ")" +
                "VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement prST = getDbConnection().prepareStatement(insert);
            prST.setString(1,user.GetNameFull());
            prST.setString(2,user.GetSurname());
            prST.setString(3,user.GetBirthday().toString());
            prST.setString(4,user.GetUserGender());
            prST.setString(5,user.GetUsername());
            prST.setString(6,user.GetPassword());

            prST.executeUpdate(); // закинуть в базу данных
        }catch(SQLException exc) {
            JOptionPane.showMessageDialog(null,exc);
        }catch (ClassNotFoundException exc){
            JOptionPane.showMessageDialog(null,exc);
        }
    }

    public ResultSet getUser(String username,String password){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + cons.USER_TABLE + "WHERE " + cons.USER_USERNAME + "=? AND " + cons.USER_PASSWORD + "=?";
        try {
            PreparedStatement prST = getDbConnection().prepareStatement(select);
            prST.setString(1,username);
            prST.setString(2,password);

           resSet = prST.executeQuery(); // получить данный из БД
            prST.executeQuery();

        }catch(SQLException exc) {
            JOptionPane.showMessageDialog(null,exc);
        }catch (ClassNotFoundException exc){
            JOptionPane.showMessageDialog(null,exc);
        }
        return resSet;

        //eсли найдет вернеть пользователя если нет то null
    }
}
