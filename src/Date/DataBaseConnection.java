package Date;

import Backend.User;

import javax.swing.*;
import java.sql.*;

public class DataBaseConnection extends config {
    Connection dbConnection;

    public Connection getDbConnection() throws SQLException, ClassNotFoundException {

        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPassword);
        return dbConnection;
    }


}
