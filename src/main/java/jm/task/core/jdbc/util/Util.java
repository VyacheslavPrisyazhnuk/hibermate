package jm.task.core.jdbc.util;


import java.sql.*;

public class Util {
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;

    static {
        URL = "jdbc:mysql://localhost:3306/mydpc?useSSL=false";
        USERNAME = "root";
        PASSWORD = "glory1357";

    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
    public Util() {
    }
}


