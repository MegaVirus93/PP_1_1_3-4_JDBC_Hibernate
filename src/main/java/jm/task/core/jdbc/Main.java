package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Витя", "Иванов", (byte) 19);
        userService.saveUser("Олег", "Сергеев", (byte) 20);
        userService.saveUser("Сергей", "Петров", (byte) 21);
        userService.saveUser("Максим", "Сидоров", (byte) 23);
        List<User> users = userService.getAllUsers();
        System.out.println(users);

        /*
        int removeByID = 2;
        userService.removeUserById(removeByID);
        System.out.printf("\nПользватель с id = %d удален\n", removeByID);
        users = userService.getAllUsers();
        users.forEach(System.out::println);
        */

        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.closeConnection();
    }
}
