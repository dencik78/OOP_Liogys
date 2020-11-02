package Date;

import Backend.User;

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
        String insert = "INSERT INTO"+ cons.USER_TABLE + "(" +
                cons.USER_NAME + "," + cons.USER_SURNAME + "," +
                cons.USER_BIRTHDAY + "," + cons.USER_GENDER + ","+
                cons.USER_USERNAME + "," + cons.USER_PASSWORD + ")" +
                "VALUES (?,?,?,?,?,?)";

        PreparedStatement prST = getDbConnection().prepareStatement(insert);
        prST.setString(1,user.GetNameFull());
        prST.setString(1,user.GetNameFull());
        prST.setString(1,user.GetNameFull());
        prST.setString(1,user.GetNameFull());
        prST.setString(5,user.GetUsername());
        prST.setString(6,user.GetPassword());


    }
}
