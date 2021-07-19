package jm.task.core.jdbc.dao;

import com.mysql.cj.jdbc.DatabaseMetaData;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {

        ResultSet tables = null;
        try {
            tables = Util.getDmb().getTables(null, null, "mytable", null);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (!tables.next()) {
                String sqlCommand = "CREATE TABLE mytable (Id INT PRIMARY KEY AUTO_INCREMENT, Name VARCHAR(20), lastName VARCHAR(20), age INT)";
                try {
                    Util.getStm().executeUpdate(sqlCommand);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        ResultSet tables = null;
        try {
            tables = Util.getDmb().getTables(null, null, "mytable", null);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            if (tables.next()) {
                Util.getStm().executeUpdate("DROP TABLE mytable");
                }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            Util.getStm().execute("INSERT INTO mytable (Name , lastName, age) VALUE ('"+name+"','"+lastName+"', '"+age+"')");
            System.out.printf("User с именем – %s добавлен в базу данных \n", name);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            Util.getStm().execute("delete from mytable where id = '"+ (int) id+"'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String query = "SELECT * FROM mytable";
        List <User> userList = new ArrayList<>();
        ResultSet tables = null;
        try {
            tables = Util.getDmb().getTables(null, null, "mytable", null);


            if (tables.next()) {
            ResultSet resultSet = Util.getStm().executeQuery(query);
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"), resultSet.getString("lastName"), resultSet.getByte("age"));
                user.setId(resultSet.getLong("Id"));
                userList.add(user);
            }
                System.out.println(userList);

        } }catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return userList;
    }

    public void cleanUsersTable() {
        try {
            Util.getStm().execute("TRUNCATE TABLE mytable");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
