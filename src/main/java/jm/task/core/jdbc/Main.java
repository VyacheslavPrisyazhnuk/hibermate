package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();
        userDao.saveUser("Ivanic", "Ivanov", (byte) 5);
        userDao.saveUser("Slava", "Prisyazhnuk", (byte) 33);
        userDao.saveUser("Vitaliy", "Geniev", (byte) 30);
        userDao.saveUser("Oleg", "Batin", (byte) 43);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}

