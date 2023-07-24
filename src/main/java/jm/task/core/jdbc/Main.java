package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;



public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Анна", "Каренина", (byte) 18);
        userService.saveUser("Михаил", "Кочевний", (byte) 34);
        userService.saveUser("София", "Овечкина", (byte) 29);
        userService.saveUser("Виктор", "Шевченко", (byte) 14);
        userService.removeUserById(2);
        userService.getAllUsers();
        userService.cleanUsersTable();

    }
}
