package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

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

