package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Util util;

    public UserDaoJDBCImpl() {
        this.util = new Util();
    }

    public void createUsersTable() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = util.getConnection();
            statement = connection.createStatement();
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS mytable (Id INT PRIMARY KEY AUTO_INCREMENT, Name VARCHAR(20), lastName VARCHAR(20), age INT)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.commit();
            } catch (SQLException throwables) {
                System.out.println("при попытке коммита произошла ошибка");
                try {
                    connection.rollback();
                    System.out.println("произошел ролбек");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
        }
    }
}
    public void dropUsersTable() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = util.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS mytable");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.commit();
            } catch (SQLException throwables) {
                System.out.println("при попытке коммита произошла ошибка");
                try {
                    connection.rollback();
                    System.out.println("произошел ролбек");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public void saveUser(String name, String lastName, byte age) {
        Connection connection = null;
        PreparedStatement prStatement = null;
        try {
            connection = util.getConnection();
            prStatement = connection.prepareStatement("INSERT INTO mytable (Name , lastName, age) VALUES (?,?,?)");
            prStatement.setString(1, name);
            prStatement.setString(2, lastName);
            prStatement.setByte(3, age);
            prStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.commit();
            } catch (SQLException throwables) {
                System.out.println("при попытке коммита произошла ошибка");
                try {
                    connection.rollback();
                    System.out.println("произошел ролбек");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                prStatement.close();
                connection.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public void removeUserById(long id) {
        Connection connection = null;
        PreparedStatement prStatement = null;
        try {
            connection = util.getConnection();
            prStatement = connection.prepareStatement("delete from mytable where id = (int) id");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    connection.commit();
                } catch (SQLException throwables) {
                    System.out.println("при попытке коммита произошла ошибка");
                    try {
                        connection.rollback();
                        System.out.println("произошел ролбек");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    prStatement.close();
                    connection.close();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
    }
    public List<User> getAllUsers() {
        String query = "SELECT * FROM mytable";
        List<User> userList = new ArrayList<>();
        ResultSet tables;
        Connection connection = null;
        Statement statement = null;
        try {
            connection = util.getConnection();
            statement = connection.createStatement();
            tables = connection.getMetaData().getTables(null, null, "mytable", null);
            if (tables.next()) {
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    User user = new User(resultSet.getString("name"), resultSet.getString("lastName"), resultSet.getByte("age"));
                    userList.add(user);
                }
                System.out.println(userList);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.commit();
            } catch (SQLException throwables) {
                System.out.println("при попытке коммита произошла ошибка");
                try {
                    connection.rollback();
                    System.out.println("произошел ролбек");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return userList;
    }
    public void cleanUsersTable() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = util.getConnection();
            statement = connection.createStatement();
            statement.execute("TRUNCATE TABLE mytable");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.commit();
            } catch (SQLException throwables) {
                System.out.println("при попытке коммита произошла ошибка");
                try {
                    connection.rollback();
                    System.out.println("произошел ролбек");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
