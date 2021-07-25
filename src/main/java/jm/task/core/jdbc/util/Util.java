package jm.task.core.jdbc.util;
import java.sql.*;
public class Util {
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        URL = "jdbc:mysql://localhost:3306/mydpc?useSSL=false";
        USERNAME = "root";
        PASSWORD = "glory1357";
    }
    public Util() {
    }
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}


