package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Util util;

    public UserDaoJDBCImpl() {
        this.util = new Util();
    }

    public void createUsersTable() {
        ResultSet tables;
        try (Connection connection = util.getConnection();
            Statement statement = connection.createStatement()) {
            tables = connection.getMetaData().getTables(null, null, "mytable", null);
            if (!tables.next()) {
                String sqlCommand = "CREATE TABLE mytable (Id INT PRIMARY KEY AUTO_INCREMENT, Name VARCHAR(20), lastName VARCHAR(20), age INT)";
                statement.executeUpdate(sqlCommand);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void dropUsersTable() {
        ResultSet tables;
        try (Connection connection = util.getConnection();
            Statement statement = connection.createStatement()) {
            tables = connection.getMetaData().getTables(null, null, "mytable", null);
            if (tables.next()) {
                statement.executeUpdate("DROP TABLE mytable");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = util.getConnection();
            Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO mytable (Name , lastName, age) VALUE ('" + name + "','" + lastName + "', '" + age + "')");
            System.out.printf("User с именем – %s добавлен в базу данных \n", name);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void removeUserById(long id) {
        try (Connection connection = util.getConnection();
            Statement statement = connection.createStatement()) {
            statement.execute("delete from mytable where id = '" + (int) id + "'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public List<User> getAllUsers() {
        String query = "SELECT * FROM mytable";
        List<User> userList = new ArrayList<>();
        ResultSet tables;
        try (Connection connection = util.getConnection();
            Statement statement = connection.createStatement()) {
            tables = connection.getMetaData().getTables(null, null, "mytable", null);
            if (tables.next()) {
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    User user = new User(resultSet.getLong("Id"), resultSet.getString("name"), resultSet.getString("lastName"), resultSet.getByte("age"));
                    userList.add(user);
                }
                System.out.println(userList);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }
    public void cleanUsersTable() {
        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("TRUNCATE TABLE mytable");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
