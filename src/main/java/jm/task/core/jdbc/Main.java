package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Виктор", "Иванов", (byte) 19);
        userService.saveUser("Олег", "Сергеев", (byte) 20);
        userService.saveUser("Сергей", "Петров", (byte) 21);
        userService.saveUser("Максим", "Сидоров", (byte) 23);
        userService.getAllUsers().forEach(System.out::println);

        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.close();
    }
}
