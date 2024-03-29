package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Витя", "Иванов", (byte) 19);
        userService.saveUser("Олег", "Сергеев", (byte) 20);
        userService.saveUser("Сергей", "Петров", (byte) 21);
        userService.saveUser("Максим", "Сидоров", (byte) 23);

        List<User> users = userService.getAllUsers();
        System.out.println(users);

        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.close();
    }
}
