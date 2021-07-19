package jm.task.core.jdbc.util;


import java.sql.*;

public class Util {
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;
    private Connection connection;
    private static Statement stm;
    private static DatabaseMetaData dbm;

    public static Statement getStm() {
        return stm;
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseMetaData getDmb() {
        return dbm;
    }
    public Util(String url, String username, String password) {
        URL = url;
        USERNAME = username;
        PASSWORD = password;

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            stm = connection.createStatement();
            dbm = connection.getMetaData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}


