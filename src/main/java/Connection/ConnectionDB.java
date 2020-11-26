package Connection;

import entity.User;

import java.sql.*;

public class ConnectionDB {
    Connection dbConnection;
    public Connection getDataBaseConnection()
            throws ClassCastException, SQLException {
        String ConnectionString = "jdbc:h2:D:/MyStudios/TestingBotTelegram/TestingBotTelegram/src/main/resources/DateBase/DateBaseTelegram";
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dbConnection = DriverManager.getConnection(ConnectionString, "root", "12345");
        return dbConnection;
    }
}
