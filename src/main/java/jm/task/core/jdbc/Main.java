package jm.task.core.jdbc;

import com.mysql.cj.Session;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        UserDao userDao = new UserDaoJDBCImpl();
        userDaoHibernate.createUsersTable();
        userDaoHibernate.saveUser("Bog", "Bogovih", (byte) 45);
        userDaoHibernate.saveUser("gaf", "strak", (byte) 33);
        userDaoHibernate.saveUser("pip", "poc", (byte) 23);
        userDaoHibernate.saveUser("nub", "ger", (byte) 76);
        userDaoHibernate.getAllUsers();
        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();
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

