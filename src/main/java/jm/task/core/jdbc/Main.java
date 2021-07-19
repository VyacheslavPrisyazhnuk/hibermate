package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        Util util = new Util("jdbc:mysql://localhost:3306/mydpc?useSSL=false","root", "glory1357");
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();
        userDao.saveUser("Ivan", "Ivanov", (byte) 5);
        userDao.saveUser("Slava", "Prisyazhnuk", (byte) 33);
        userDao.saveUser("Vitaliy", "Geniev", (byte) 30);
        userDao.saveUser("Oleg", "Batin", (byte) 43);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();

        try {
            util.getConnection().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    }

