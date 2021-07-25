package jm.task.core.jdbc;

import com.mysql.cj.Session;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Bog", "Bogovih", (byte) 45);
        userService.saveUser("gaf", "strak", (byte) 33);
        userService.saveUser("pip", "poc", (byte) 23);
        userService.saveUser("nub", "ger", (byte) 76);
        userService.getAllUsers();
        userService.removeUserById(3);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

